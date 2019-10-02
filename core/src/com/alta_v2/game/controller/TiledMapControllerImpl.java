package com.alta_v2.game.controller;

import com.alta_v2.game.GameManager;
import com.alta_v2.game.screen.TiledMapScreen;
import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Provides the controller of tiled map screen.
 */
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class TiledMapControllerImpl implements TiledMapController {

    private final GameManager gameManager;

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveBy(float x, float y) {
        TiledMapScreen tiledMapScreen = this.gameManager.getScreenAsType(TiledMapScreen.class);

        if (tiledMapScreen == null) {
            log.warn("Failed move by operation due tiled map screen not found.");
            return;
        }

        tiledMapScreen.moveAllBy(x, y);
    }
}
