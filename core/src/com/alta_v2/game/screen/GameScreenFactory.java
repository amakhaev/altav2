package com.alta_v2.game.screen;

import com.alta_v2.mediatorModule.screen.context.ScreenContext;

/**
 * Provides the factory to generate screen.
 */
public interface GameScreenFactory {

    /**
     * Creates the game screen to render all UI components.
     *
     * @param screenContext     - the {@link ScreenContext} instance.
     * @return created {@link GameScreen} instance.
     */
    GameScreen createGameScreen(ScreenContext screenContext);

}
