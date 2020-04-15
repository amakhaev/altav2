package com.alta_v2.rendering.tiledMapScreen.layout

import com.alta_v2.rendering.Renderer
import com.alta_v2.rendering.ScreenState

/**
 * Provides the renderable layout.
 */
interface ObjectLayout : Renderer {

    fun init(state: ScreenState, tileWidth: Int, tileHeight: Int)

}