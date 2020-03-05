package com.alta_v2.mediator;

import com.alta_v2.mediator.screen.context.ScreenContext;
import com.alta_v2.model.MenuDefinitionModel;
import com.alta_v2.model.TiledMapDefinitionModel;

/**
 * Provides the mediator that responsible for orchestration of game.
 */
public interface ProcessMediator {

    /**
     * Gets context of current screen.
     */
    ScreenContext getCurrentContext();

    /**
     * Loads the menu screen.
     */
    void loadMenuScreen(MenuDefinitionModel definition);

    /**
     * Loads a tiled map screen.
     */
    void loadTiledMapScreen(TiledMapDefinitionModel definition);
}
