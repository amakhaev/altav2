package com.alta_v2.game.gamelogic.domain.player;

import com.alta_v2.facade.tiledMapApi.TiledMapCoreApi;
import com.alta_v2.game.gamelogic.utils.LogicThreadFactory;
import com.alta_v2.mediator.serde.ActionListener;
import com.google.common.collect.Sets;
import com.google.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

@Log4j2
public class PlayerProcessorImpl implements PlayerProcessor {

    private static final String LISTENER_THREAD_NAME = "map-stage-listener";

    private final Set<ActionListener.ActionType> actionStatus = Sets.newConcurrentHashSet();
    private final Consumer<ActionListener.ActionType> statusConsumer = this::resolveAction;

    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(
            1, new LogicThreadFactory(LISTENER_THREAD_NAME)
    );

    private final TiledMapCoreApi tiledMapCoreApi;


    @Inject
    public PlayerProcessorImpl(TiledMapCoreApi tiledMapCoreApi) {
        this.tiledMapCoreApi = tiledMapCoreApi;
        this.executorService.scheduleWithFixedDelay(this::checkActions, 0L, 10L, TimeUnit.MILLISECONDS);
    }

    @Override
    public void actionBegin(ActionListener.ActionType action) {
        this.actionStatus.add(action);
    }

    @Override
    public void actionFinish(ActionListener.ActionType action) {
        this.actionStatus.remove(action);
    }

    @Override
    public void destroy() {
        try {
            this.executorService.shutdown();
        } catch (Exception e) {
            log.error(e);
        }
    }

    private void checkActions() {
        try {
            if (actionStatus.isEmpty()) {
                return;
            }

            actionStatus.forEach(statusConsumer);
        } catch (Exception e) {
            log.error(e);
        }
    }

    private void resolveAction(ActionListener.ActionType actionType) {
        switch (actionType) {
            case MOVE_UP:
            case MOVE_DOWN:
            case MOVE_LEFT:
            case MOVE_RIGHT:
                this.tiledMapCoreApi.performPlayerMovement(ActionListener.ActionType.getMovementDirection(actionType));
                break;
            case BACK:
            case NEXT:
                break;
        }
    }
}
