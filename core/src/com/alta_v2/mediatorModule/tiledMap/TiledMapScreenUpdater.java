package com.alta_v2.mediatorModule.tiledMap;

import com.alta_v2.mediatorModule.serde.Updater;
import com.alta_v2.physicsModule.TiledMapPhysicEngine;
import com.alta_v2.renderingModule.ScreenState;
import com.alta_v2.renderingModule.tiledMapScreen.TiledMapState;
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

        this.engine.updateState(state);
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
