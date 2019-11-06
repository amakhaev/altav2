package com.alta_v2.game.screen;

import com.alta_v2.game.AltaV2;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.google.inject.Inject;
import lombok.Getter;

/**
 * Provides the manager of screens.
 */
public class GameManager implements ScreenSwitcher {

    @Getter
    private AltaV2 game;
    // private final ScreenFactory screenFactory;

    private InputListener inputListener;

    /**
     * Initialize new instance of {@link GameManager}.
     *
     * @param screenFactory - the {@link ScreenFactory} instance.
     */
    /*@Inject
    public GameManager(ScreenFactory screenFactory) {
        this.screenFactory = screenFactory;
        this.game = new AltaV2();
    }*/

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeTiledMap() {
        TiledMapScreenImpl oldScreen = this.getScreenAsType(TiledMapScreenImpl.class);

        if (!this.isInstanceOf(TiledMapScreenImpl.class, oldScreen)) {
            throw new RuntimeException("Game screen has invalid type");
        }

        oldScreen.fadeOutScreen(() -> {
            //TiledMapScreenImpl newTiledMapScreenImpl = this.screenFactory.createTiledMapScreen(this.inputListener);
            //this.game.setScreen(newTiledMapScreenImpl);
            oldScreen.dispose();
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInitialScreen() {
        //TiledMapScreenImpl tiledMapScreenImpl = this.screenFactory.createTiledMapScreen(this.inputListener);
        //this.game.setScreen(tiledMapScreenImpl);
    }

    /**
     * Gets the screen as one of child type.
     *
     * @param resultType - the type that will returns.
     * @return the resultType instance or null;
     */
    public <T extends Screen> T getScreenAsType(Class<T> resultType) {
        Screen screen = this.game.getScreen();
        if (!this.isInstanceOf(TiledMapScreenImpl.class, screen)) {
            return null;
        }

        return resultType.cast(screen);
    }

    /**
     * Sets the input listener to the screen.
     *
     * @param inputListener - the {@link InputListener} instance.
     */
    public void setInputListener(InputListener inputListener) {
        this.inputListener = inputListener;

        TiledMapScreenImpl screen = this.getScreenAsType(TiledMapScreenImpl.class);;
        if (screen == null) {
            return;
        }

        if (!this.isInstanceOf(TiledMapScreenImpl.class, screen)) {
            throw new RuntimeException("Game screen has invalid type");
        }

        screen.setInputListener(inputListener);
    }

    private <T> boolean isInstanceOf(Class<T> clazz, Screen screen) {
        return clazz.isInstance(screen);
    }
}
