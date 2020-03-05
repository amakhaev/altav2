package com.alta_v2.mediator.serde;

import com.alta_v2.mediator.screen.menu.MenuScreenUpdater;
import com.alta_v2.mediator.screen.tiledMap.TiledMapScreenUpdater;
import com.alta_v2.physics.TiledMapPhysicEngine;

/**
 * Provides the factory for creating of screen updaters.
 */
public interface UpdaterFactory {

    /**
     * Creates the {@link MenuScreenUpdater} instance.
     */
    MenuScreenUpdater createMenuScreenUpdater();

    /**
     * Creates the {@link TiledMapScreenUpdater} instance.
     */
    TiledMapScreenUpdater createTiledMapScreenUpdater(TiledMapPhysicEngine engine);
}
