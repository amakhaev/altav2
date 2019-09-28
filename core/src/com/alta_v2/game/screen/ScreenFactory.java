package com.alta_v2.game.screen;

/**
 * Provides the factory for screens.
 */
public interface ScreenFactory {

    /**
     * Creates the tiled map screen.
     *
     * @return created {@link TiledMapScreen} instance.
     */
    TiledMapScreen createTiledMapScreen();

}
