package com.alta_v2.game.controller;

import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Provides the controller to handle input processors.
 */
public interface InputController {

    /**
     * Sets the input listener to the screen.
     *
     * @param inputListener - the {@link InputListener} instance.
     */
     void setInputListener(InputListener inputListener);

}
