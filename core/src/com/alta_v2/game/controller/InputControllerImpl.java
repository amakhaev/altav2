package com.alta_v2.game.controller;

import com.alta_v2.game.GameManager;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;

/**
 * Provides the controller to handle input processors.
 */
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class InputControllerImpl implements InputController {

    private final GameManager gameManager;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInputListener(InputListener inputListener) {
        this.gameManager.setInputListener(inputListener);
    }
}
