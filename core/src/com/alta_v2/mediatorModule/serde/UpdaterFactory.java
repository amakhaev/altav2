package com.alta_v2.mediatorModule.serde;

import com.alta_v2.mediatorModule.menu.MenuScreenUpdater;
import com.alta_v2.mediatorModule.tiledMap.TiledMapScreenUpdater;
import com.alta_v2.physicsModule.TiledMapPhysicEngine;

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
