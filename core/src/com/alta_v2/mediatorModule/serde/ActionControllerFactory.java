package com.alta_v2.mediatorModule.serde;

import com.alta_v2.mediatorModule.menu.MenuActionController;
import com.alta_v2.mediatorModule.tiledMap.TiledMapActionController;

/**
 * Provides the factory for creating controller actions.
 */
public interface ActionControllerFactory {

    /**
     * Creates the {@link TiledMapActionController} instance.
     */
    TiledMapActionController createTiledMapActionController();

    /**
     * Creates the {@link MenuActionController} instance.
     */
    MenuActionController createMenuActionController();
}
