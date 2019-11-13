package com.alta_v2.mediatorModule;

/**
 * Provides the mediator that responsible for orchestration of game.
 */
public interface ProcessMediator {

    /**
     * Loads the menu screen.
     */
    void loadMenuScreen();

    /**
     * Loads a tiled map screen.
     */
    void loadTiledMapScreen();

    /**
     * Gets the context of current loaded screen.
     *
     * @return the {@link ScreenContext} instance.
     */
    ScreenContext getCurrentContext();
}
