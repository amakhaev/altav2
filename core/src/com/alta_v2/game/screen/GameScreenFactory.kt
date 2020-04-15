package com.alta_v2.game.screen

import com.alta_v2.mediator.screen.context.ScreenContext

/**
 * Provides the factory to generate screen.
 */
interface GameScreenFactory {
    /**
     * Creates the game screen to render all UI components.
     *
     * @param screenContext     - the [ScreenContext] instance.
     * @return created [GameScreen] instance.
     */
    fun createGameScreen(screenContext: ScreenContext): GameScreen
}