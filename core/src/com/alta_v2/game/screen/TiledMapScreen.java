package com.alta_v2.game.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Provides the screen to represent tiled maps.
 */
public interface TiledMapScreen extends Screen {

    /**
     * Sets the input listener to the screen.
     *
     * @param inputListener - the {@link InputListener} instance.
     */
    void setInputListener(InputListener inputListener);

    /**
     * Fades out the screen.
     *
     * @param postAction - the post action to be executed after sequence.
     */
    void fadeOutScreen(Runnable postAction);

    /**
     * Moves stage by given target point.
     *
     * @param x - the target X by be moved.
     * @param y - the target Y by be moved.
     */
    void moveAllBy(float x, float y);

}
