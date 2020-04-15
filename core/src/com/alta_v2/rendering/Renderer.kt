package com.alta_v2.rendering

import com.alta_v2.common.Destroyable

/**
 * Provides the high level abstraction for components that can be rendered.
 */
interface Renderer : Destroyable {
    /**
     * Initializes the renderable component.
     */
    fun init(state: ScreenState)

    /**
     * Renders the component.
     *
     * @param delta - the time in seconds since the last render
     */
    fun render(delta: Float, state: ScreenState)
}