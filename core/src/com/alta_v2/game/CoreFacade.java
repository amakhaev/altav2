package com.alta_v2.game;

import com.badlogic.gdx.Game;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Provides the facade to manipulate with core.
 */
public class CoreFacade {

    private final GameManager gameManager;

    /**
     * Initialize new instance of {@link CoreFacade}.
     */
    public CoreFacade() {
        Injector coreInjector = Guice.createInjector(new CoreInjector());
        this.gameManager = coreInjector.getInstance(GameManager.class);
    }

    public Game getGame() {
        return this.gameManager.getGame();
    }

}
