package com.alta_v2.mediatorModule.tiledMap;

import com.alta_v2.mediatorModule.serde.ActionController;
import com.alta_v2.physicsModule.TiledMapPhysicEngine;
import com.google.common.collect.Sets;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import lombok.extern.log4j.Log4j2;

import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Provides the controller of actions on tiled map.
 */
@Log4j2
public class TiledMapActionController implements ActionController {

    private static final String LISTENER_THREAD_NAME = "tiledmap-listener";

    private final ScheduledExecutorService executorService;
    private final Set<ActionType> actionStatus;
    private final TiledMapPhysicEngine physicEngine;

    private int threadCounter;

    /**
     * Initialize new instance of {@link TiledMapActionController}.
     *
     * @param physicEngine - the {@link TiledMapPhysicEngine} instance.
     */
    @AssistedInject
    public TiledMapActionController(@Assisted TiledMapPhysicEngine physicEngine) {
        this.physicEngine = physicEngine;
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
    }

    private void checkActions() {
        this.actionStatus.forEach(status -> {
            switch (status) {
                case MOVE_UP:
                case MOVE_DOWN:
                case MOVE_LEFT:
                case MOVE_RIGHT:
                    this.runMovement(status);
                    break;
                case BACK:
                case NEXT:
                    log.info("Action not handled yet");
                    break;
            }
        });
    }

    private void runMovement(ActionType type) {
        if (this.physicEngine.canMoveFocusPoint()) {
            this.physicEngine.performFocusPointMovement(ActionType.getMovementDirection(type));
        }
    }
}
