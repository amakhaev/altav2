package com.alta_v2.game;

import com.alta_v2.game.inputProcessor.InputProcessorFactory;
import com.alta_v2.game.screen.ScreenFactory;
import com.alta_v2.game.screen.TiledMapScreen;
import com.badlogic.gdx.Screen;
import com.google.inject.Inject;
import lombok.Getter;

/**
 * Provides the manager of screens.
 */
public class GameManager implements ScreenSwitcher {

    @Getter
    private final AltaV2 game;
    private final ScreenFactory screenFactory;

    private int i = 1;

    /**
     * Initialize new instance of {@link GameManager}.
     *
     * @param screenFactory - the {@link ScreenFactory} instance.
     */
    @Inject
    public GameManager(ScreenFactory screenFactory, InputProcessorFactory inputProcessorFactory) {
        this.screenFactory = screenFactory;
        this.game = new AltaV2(this, inputProcessorFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeTiledMap() {
        Screen oldScreen = this.game.getScreen();
        TiledMapScreen newTiledMapScreen = this.screenFactory.createTiledMapScreen();

        ((TiledMapScreen) this.game.getScreen()).fadeOutScreen(() -> {
            this.game.setScreen(newTiledMapScreen);
            if (oldScreen != null) {
                oldScreen.dispose();
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInitialScreen() {
        TiledMapScreen tiledMapScreen = this.screenFactory.createTiledMapScreen();
        this.game.setScreen(tiledMapScreen);
    }
}
