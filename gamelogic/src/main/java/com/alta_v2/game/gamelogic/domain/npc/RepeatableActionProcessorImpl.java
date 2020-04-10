package com.alta_v2.game.gamelogic.domain.npc;

import com.alta_v2.facade.tiledMapApi.TiledMapCoreApi;
import com.alta_v2.game.gamelogic.data.npc.NpcModel;
import com.alta_v2.game.gamelogic.utils.LogicThreadFactory;
import com.alta_v2.physics.task.MovementDirection;
import com.alta_v2.physics.task.resultObserver.TaskResultObserver;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class RepeatableActionProcessorImpl implements RepeatableActionProcessor {

    private static final String LISTENER_THREAD_NAME = "repeatable-action-processing";

    private final List<NpcModel> npcList = Lists.newCopyOnWriteArrayList();
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(
            1, new LogicThreadFactory(LISTENER_THREAD_NAME)
    );
    private final TiledMapCoreApi tiledMapCoreApi;

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToRandomMovement(NpcModel npc) {
        npc.setLastMovementMills(System.currentTimeMillis());
        this.npcList.add(npc);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startAsync() {
        this.executorService.scheduleWithFixedDelay(this::doNpcMovement, 0L, 100L, TimeUnit.MILLISECONDS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
        try {
            this.executorService.shutdown();
        } catch (Exception e) {
            log.error(e);
        }
        this.npcList.clear();
    }

    private void doNpcMovement() {
        this.npcList.forEach(npc -> {
            if (npc.isMovementRunning()) {
                return;
            }

            if (System.currentTimeMillis() - npc.getLastMovementMills() < npc.getRepeatMovementInterval()) {
                return;
            }

            TaskResultObserver observer = tiledMapCoreApi.performNpcMovement(npc.getId(), MovementDirection.randomDirection());
            if (observer != null) {
                npc.setMovementRunning(true);
                observer.subscribeOnComplete(() -> {
                    npc.setMovementRunning(false);
                    npc.setLastMovementMills(System.currentTimeMillis());
                });
            }
        });
    }
}
