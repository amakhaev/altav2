package com.alta_v2.mediatorModule.serde;

import com.alta_v2.mediatorModule.menu.MenuActionController;
import com.alta_v2.mediatorModule.tiledMap.TiledMapActionController;
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
