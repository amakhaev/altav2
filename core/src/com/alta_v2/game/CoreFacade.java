package com.alta_v2.game;

import com.alta_v2.game.controller.InputController;
import com.alta_v2.game.controller.TiledMapController;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

/**
 * Provides the facade to manipulate with core.
 */
@Log4j2
public class CoreFacade {

    private final GameManager gameManager;

    @Getter
    private final InputController inputController;

    @Getter
    private final TiledMapController tiledMapController;

    /**
     * Initialize new instance of {@link CoreFacade}.
     */
    public CoreFacade() {
        Injector coreInjector = Guice.createInjector(new CoreInjector());

        this.gameManager = coreInjector.getInstance(GameManager.class);
        this.inputController = coreInjector.getInstance(InputController.class);
        this.tiledMapController = coreInjector.getInstance(TiledMapController.class);

        this.inputController.setInputListener(this.createInputListener());
    }

    /**
     * Gets the game.
     *
     * @return the {@link Game} instance.
     */
    public Game getGame() {
        return this.gameManager.getGame();
    }

    private InputListener createInputListener() {

        return new InputListener() {
            public boolean keyTyped (InputEvent event, char character) {
                if (character == 'm') {
                    log.info("Java heap size: " + Gdx.app.getJavaHeap() / 1024 / 1024 + " MB");
                    log.info("Native heap size: " + Gdx.app.getNativeHeap() / 1024 / 1024 + " MB");
                }

                if (character == 'c') {
                    tiledMapController.loadScreen();
                }
                return false;
            }
        };
    }
}
