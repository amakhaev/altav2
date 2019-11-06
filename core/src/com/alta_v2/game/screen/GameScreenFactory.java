package com.alta_v2.game.screen;

import com.alta_v2.renderingModule.Renderable;

/**
 * Provides the factory to generate screen.
 */
public interface GameScreenFactory {

    /**
     * Creates the game screen to render all UI components.
     *
     * @param renderable - the renderable component to be rendered.
     * @return created {@link GameScreen} instance.
     */
    GameScreen createGameScreen(Renderable renderable);

}
