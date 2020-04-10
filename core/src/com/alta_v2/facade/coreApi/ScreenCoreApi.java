package com.alta_v2.facade.coreApi;

import com.alta_v2.game.utils.ChangeScreenResult;
import com.alta_v2.model.MenuDefinitionModel;
import com.alta_v2.model.TiledMapDefinitionModel;

/**
 * Provides the API for core project.
 */
public interface ScreenCoreApi {

    /**
     * Loads the menu screen.
     */
    ChangeScreenResult loadMenuScreen(MenuDefinitionModel definition);

    /**
     * Loads a tiled map screen.
     */
    ChangeScreenResult loadTiledMapScreen(TiledMapDefinitionModel definition);

}
