package com.alta_v2.game;

import com.alta_v2.game.inputProcessor.InputProcessorFactory;
import com.alta_v2.game.screen.ScreenFactory;
import com.alta_v2.game.screen.TiledMapScreen;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.google.inject.Inject;
import lombok.Getter;
import lombok.Setter;

/**
 * Provides the manager of screens.
 */
public class GameManager implements ScreenSwitcher {

    @Getter
    private final AltaV2 game;
    private final ScreenFactory screenFactory;

    private InputListener inputListener;

    /**
     * Initialize new instance of {@link GameManager}.
     *
     * @param screenFactory - the {@link ScreenFactory} instance.
     */
    @Inject
    public GameManager(ScreenFactory screenFactory) {
        this.screenFactory = screenFactory;
        this.game = new AltaV2(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeTiledMap() {
        Screen oldScreen = this.game.getScreen();
        TiledMapScreen newTiledMapScreen = this.screenFactory.createTiledMapScreen(this.inputListener);

        if (!this.isInstanceOf(TiledMapScreen.class, oldScreen)) {
            throw new RuntimeException("Game screen has invalid type");
        }

        TiledMapScreen tiledMapScreen = (TiledMapScreen) oldScreen;
        tiledMapScreen.fadeOutScreen(() -> {
            this.game.setScreen(newTiledMapScreen);
            oldScreen.dispose();
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInitialScreen() {
        TiledMapScreen tiledMapScreen = this.screenFactory.createTiledMapScreen(this.inputListener);
        this.game.setScreen(tiledMapScreen);
    }

    /**
     * Sets the input listener to the screen.
     *
     * @param inputListener - the {@link InputListener} instance.
     */
    public void setInputListener(InputListener inputListener) {
        this.inputListener = inputListener;

        Screen screen = this.game.getScreen();
        if (screen == null) {
            return;
        }

        if (!this.isInstanceOf(TiledMapScreen.class, screen)) {
            throw new RuntimeException("Game screen has invalid type");
        }

        TiledMapScreen tiledMapScreen = (TiledMapScreen) screen;
        tiledMapScreen.setInputListener(inputListener);
    }

    private <T> boolean isInstanceOf(Class<T> clazz, Screen screen) {
        return clazz.isInstance(screen);
    }
}
