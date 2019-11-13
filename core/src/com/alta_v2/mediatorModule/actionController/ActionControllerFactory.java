package com.alta_v2.mediatorModule.actionController;

import com.alta_v2.mediatorModule.actionController.tiledMap.TiledMapActionController;

/**
 * Provides the factory for creating controller actions.
 */
public interface ActionControllerFactory {

    /**
     * Creates the {@link TiledMapActionController} instance.
     */
    TiledMapActionController createTiledMapActionController();

}
