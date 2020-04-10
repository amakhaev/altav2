package com.alta_v2.mediator.screen.tiledMap;

import com.alta_v2.mediator.serde.Updater;
import com.alta_v2.physics.TiledMapPhysicEngine;
import com.alta_v2.rendering.ScreenState;
import com.alta_v2.rendering.tiledMapScreen.TiledMapState;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import lombok.extern.log4j.Log4j2;

/**
 * {@inheritDoc}
 */
@Log4j2
public class TiledMapScreenUpdater implements Updater {

    private final TiledMapPhysicEngine engine;

    /**
     * Initialize new instance of {@link TiledMapScreenUpdater}.
     *
     * @param engine - the {@link TiledMapPhysicEngine} instance.
     */
    @AssistedInject
    public TiledMapScreenUpdater(@Assisted TiledMapPhysicEngine engine) {
        this.engine = engine;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(ScreenState screenState) {
        this.engine.processInit();
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
            this.engine.processAct(delta);
            this.engine.updateState(state);
        } catch (Exception e) {
            log.error(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
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
