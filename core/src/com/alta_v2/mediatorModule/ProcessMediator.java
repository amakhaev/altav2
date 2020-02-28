package com.alta_v2.mediatorModule;

import com.alta_v2.mediatorModule.screen.ScreenContext;

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
    void loadMenuScreen();

    /**
     * Loads a tiled map screen.
     */
    void loadTiledMapScreen();
}
