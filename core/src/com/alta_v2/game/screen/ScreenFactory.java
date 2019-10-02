package com.alta_v2.game.screen;

import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Provides the factory for screens.
 */
public interface ScreenFactory {

    /**
     * Creates the tiled map screen.
     *
     * @return created {@link InputListener} instance.
     */
    TiledMapScreenImpl createTiledMapScreen(InputListener inputListener);

}
