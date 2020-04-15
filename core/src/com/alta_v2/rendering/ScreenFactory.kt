package com.alta_v2.rendering

import com.alta_v2.rendering.menuScreen.MenuScreen
import com.alta_v2.rendering.tiledMapScreen.TiledMapMetadata
import com.alta_v2.rendering.tiledMapScreen.TiledMapScreen

/**
 * Provides the factory for creating screens.
 */
interface ScreenFactory {
    /**
     * Creates the screen that represents the game menu.
     *
     * @return created [MenuScreen] instance.
     */
    fun createMenuScreen(): MenuScreen

    /**
     * Creates the screen that represents a game tiled map.
     *
     * @return created [TiledMapScreen] instance.
     */
    fun createTiledMapScreen(metadata: TiledMapMetadata): TiledMapScreen
}