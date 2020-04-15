package com.alta_v2.rendering.tiledMapScreen.layout.map

import com.alta_v2.common.Destroyable
import com.alta_v2.rendering.ScreenState

/**
 * Provides the layout to render a tiled map.
 */
interface MapLayout : Destroyable {
    /**
     * Initializes the renderable component.
     */
    fun init(state: ScreenState)

    /**
     * Applies the screen state to layout.
     *
     * @param state - the state to be used for rendering.
     */
    fun applyState(state: ScreenState)

    /**
     * Renders the bottom part of tiled map.
     */
    fun renderBottomPart()

    /**
     * Renders the top part of tiled map.
     */
    fun renderTopPart()

    /**
     * Get width of one tile.
     */
    fun getTileWidth(): Int

    /**
     * Get height of one tile.
     */
    fun getTileHeight(): Int
}