package com.alta_v2.game.controller;

import com.alta_v2.game.GameManager;
import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;

/**
 * Provides the controller to make actions with scenes.
 */
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ScreenControllerImpl implements ScreenController {

    private final GameManager gameManager;

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadScreen() {
        this.gameManager.changeTiledMap();
    }

}
