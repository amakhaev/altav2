package com.alta_v2.rendering

import com.alta_v2.rendering.menuScreen.MenuState
import com.alta_v2.rendering.tiledMapScreen.TiledMapState

/**
 * Provides the factory for state of screens.
 */
interface ScreenStateFactory {
    /**
     * Creates the instance of [MenuState].
     */
    fun createMenuState(): MenuState

    /**
     * Creates the instance of [TiledMapState].
     */
    fun createTiledMapState(npcIds: List<Int>): TiledMapState
}