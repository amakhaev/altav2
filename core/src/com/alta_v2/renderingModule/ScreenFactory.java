package com.alta_v2.renderingModule;

import com.alta_v2.renderingModule.menuScreen.MenuScreen;
import com.alta_v2.renderingModule.tiledMapScreen.TiledMapScreen;

/**
 * Provides the factory for creating screens.
 */
public interface ScreenFactory {

    /**
     * Creates the screen that represents the game menu.
     *
     * @return created {@link MenuScreen} instance.
     */
    MenuScreen createMenuScreen();

    /**
     * Creates the screen that represents a game tiled map.
     *
     * @return created {@link TiledMapScreen} instance.
     */
    TiledMapScreen createTiledMapScreen();

}
