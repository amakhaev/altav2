package com.alta_v2.game.gamelogic.stage;

import com.alta_v2.facade.tiledMapApi.TiledMapApi;
import com.alta_v2.game.gamelogic.stage.event.ChangeMapStageEvent;
import com.alta_v2.game.gamelogic.stage.event.ChangeMenuStageEvent;
import com.google.common.collect.Sets;
import com.google.inject.assistedinject.AssistedInject;
import lombok.extern.log4j.Log4j2;

import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Log4j2
public class MapStage extends AbstractStage {

    private static final String LISTENER_THREAD_NAME = "tiledmap-listener";

    private final ScheduledExecutorService executorService;
    private final Set<ActionType> actionStatus;
    private final TiledMapApi tiledMapApi;

    private int threadCounter;

    @AssistedInject
    public MapStage(TiledMapApi tiledMapApi) {
        this.tiledMapApi = tiledMapApi;
        this.threadCounter = 0;
        this.actionStatus = Sets.newConcurrentHashSet();
        this.executorService = Executors.newScheduledThreadPool(
                1,
                runnable -> new Thread(runnable, LISTENER_THREAD_NAME + this.threadCounter++)
        );
        this.executorService.scheduleWithFixedDelay(this::checkActions, 0L, 10L, TimeUnit.MILLISECONDS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onActionBegin(ActionType action) {
        this.actionStatus.add(action);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onActionFinish(ActionType action) {
        this.actionStatus.remove(action);

        if (action == ActionType.BACK) {
            this.changeStage(new ChangeMapStageEvent());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
        super.destroy();
        try {
            this.executorService.shutdown();
        } catch (Exception e) {
            log.error(e);
        }
    }

    private void checkActions() {
        this.actionStatus.forEach(status -> {
            switch (status) {
                case MOVE_UP:
                case MOVE_DOWN:
                case MOVE_LEFT:
                case MOVE_RIGHT:
                    this.tiledMapApi.performPlayerMovement(ActionType.getMovementDirection(status));
                    break;
                case BACK:
                case NEXT:
                    break;
            }
        });
    }
}
