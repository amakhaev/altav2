package com.alta_v2.game.screen;

import com.alta_v2.mediatorModule.updater.Updater;
import com.alta_v2.renderingModule.Renderer;

/**
 * Provides the factory to generate screen.
 */
public interface GameScreenFactory {

    /**
     * Creates the game screen to render all UI components.
     *
     * @param renderer  - the renderable component to be rendered.
     * @param updater   - the {@link Updater} instance.
     * @return created {@link GameScreen} instance.
     */
    GameScreen createGameScreen(Renderer renderer, Updater updater);

}
