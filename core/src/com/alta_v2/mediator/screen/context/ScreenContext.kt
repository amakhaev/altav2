package com.alta_v2.mediator.screen.context

import com.alta_v2.mediator.serde.Updater
import com.alta_v2.physics.PhysicEngine
import com.alta_v2.rendering.Renderer
import com.alta_v2.rendering.ScreenState

/**
 * Provides the context for screen. It's required object for rendering of any screen.
 */
class ScreenContext(val screenUpdater: Updater,
                    val screenRender: Renderer,
                    val screenState: ScreenState,
                    val dialogRender: Renderer,
                    val dialogState: ScreenState,
                    val physicEngine: PhysicEngine) {
    /**
     * Destroys the context.
     */
    fun destroy() {
        screenUpdater.destroy()
        screenRender.destroy()
    }
}