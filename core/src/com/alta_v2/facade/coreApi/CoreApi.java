package com.alta_v2.facade.coreApi;

import com.alta_v2.model.MenuDefinitionModel;
import com.alta_v2.model.TiledMapDefinitionModel;

/**
 * Provides the API for core project.
 */
public interface CoreApi {

    /**
     * Loads the menu screen.
     */
    void loadMenuScreen(MenuDefinitionModel definition);

    /**
     * Loads a tiled map screen.
     */
    void loadTiledMapScreen(TiledMapDefinitionModel definition);

}
