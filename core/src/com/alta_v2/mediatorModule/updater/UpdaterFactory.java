package com.alta_v2.mediatorModule.updater;

import com.alta_v2.mediatorModule.updater.menuScreenUpdater.MenuScreenUpdater;
import com.alta_v2.mediatorModule.updater.tiledMapScreenUpdater.TiledMapScreenUpdater;

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
