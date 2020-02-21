package com.alta_v2.mediatorModule.serde;

import com.alta_v2.mediatorModule.screen.menu.MenuActionController;
import com.alta_v2.mediatorModule.screen.tiledMap.TiledMapActionController;
import com.alta_v2.physicsModule.TiledMapPhysicEngine;

/**
 * Provides the factory for creating controller actions.
 */
public interface ActionControllerFactory {

    /**
     * Creates the {@link TiledMapActionController} instance.
     */
    TiledMapActionController createTiledMapActionController(TiledMapPhysicEngine engine);

    /**
     * Creates the {@link MenuActionController} instance.
     */
    MenuActionController createMenuActionController();
}
