package com.alta_v2.mediatorModule.updater;

import com.alta_v2.mediatorModule.updater.menu.MenuScreenUpdater;
import com.alta_v2.mediatorModule.updater.tiledMap.TiledMapScreenUpdater;

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
    TiledMapScreenUpdater createTiledMapScreenUpdater();
}
