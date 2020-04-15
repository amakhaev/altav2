package com.alta_v2.mediator.serde

import com.alta_v2.common.Destroyable
import com.alta_v2.rendering.ScreenState

/**
 * Provides the high level abstraction for update screen state.
 */
interface Updater : Destroyable {
    /**
     * Initializes the updater.
     */
    fun init(screenState: ScreenState)

    /**
     * Updates the screen.
     *
     * @param delta - the time in seconds since the last render.
     */
    fun update(delta: Float, screenState: ScreenState)
}