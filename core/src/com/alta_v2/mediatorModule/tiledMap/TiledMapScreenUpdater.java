package com.alta_v2.mediatorModule.tiledMap;

import com.alta_v2.mediatorModule.serde.Updater;
import com.alta_v2.physicsModule.TiledMapPhysicEngine;
import com.alta_v2.renderingModule.ScreenState;
import com.alta_v2.renderingModule.tiledMapScreen.TiledMapState;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * {@inheritDoc}
 */
@Log4j2
public class TiledMapScreenUpdater implements Updater {

    private static final String UPDATER_THREAD_NAME = "tiledmap-listener";

    private final TiledMapPhysicEngine engine;
    private final ExecutorService executorService;

    private byte threadCounter = 0;

    /**
     * Initialize new instance of {@link TiledMapScreenUpdater}.
     *
     * @param engine - the {@link TiledMapPhysicEngine} instance.
     */
    @AssistedInject
    public TiledMapScreenUpdater(@Assisted TiledMapPhysicEngine engine) {
        this.engine = engine;
        this.executorService = Executors.newScheduledThreadPool(
                1,
                runnable -> new Thread(runnable, UPDATER_THREAD_NAME + this.threadCounter++)
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(ScreenState screenState) {
        this.engine.init();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(float delta, ScreenState screenState) {
        TiledMapState state = this.resolveClass(screenState);
        if (state == null) {
            return;
        }

        try {
            Future<?> result = this.executorService.submit(() -> {
                this.engine.act(delta);
                this.engine.updateState(state);
            });
            // wait for result.
            result.get();
        } catch (Exception e) {
            log.error(e);
        }
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
        } finally {
            this.executorService.shutdownNow();
        }
    }

    private TiledMapState resolveClass(ScreenState screenState) {
        try {
            return ScreenState.resolveState(screenState, TiledMapState.class);
        } catch (ClassCastException e) {
            log.error(e);
            return null;
        }
    }
}
