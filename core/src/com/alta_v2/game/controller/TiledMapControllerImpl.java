package com.alta_v2.game.controller;

import com.alta_v2.game.GameManager;
import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;

/**
 * Provides the controller of tiled map screen.
 */
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class TiledMapControllerImpl implements TiledMapController {

    private final GameManager gameManager;

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadScreen() {
        this.gameManager.changeTiledMap();
    }
}
