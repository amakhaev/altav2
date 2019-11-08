package com.alta_v2.game;

import com.alta_v2.game.screen.GameScreen;
import com.alta_v2.game.screen.GameScreenFactory;
import com.alta_v2.mediatorModule.updater.Updater;
import com.alta_v2.renderingModule.Renderer;
import com.badlogic.gdx.Screen;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Provides the manager to handle screens.
 */
@Log4j2
public class ScreenManager {

    private final AltaV2 game;
    private final GameScreenFactory screenFactory;
    private final AtomicBoolean screenChangeIsLocked;

    /**
     * Initialize new instance of {@link ScreenManager}.
     *
     * @param game          - the {@link AltaV2} instance.
     * @param screenFactory - the {@link GameScreenFactory} instance.
     */
    public ScreenManager(AltaV2 game, GameScreenFactory screenFactory) {
        this.game = game;
        this.screenFactory = screenFactory;
        this.screenChangeIsLocked = new AtomicBoolean(false);
    }

    /**
     * Changes the screen.
     *
     * @param renderer  - the component to be rendered.
     * @param updater   - the {@link Updater} instance.
     */
    public void changeScreen(Renderer renderer, Updater updater) {
        if (this.screenChangeIsLocked.get()) {
            log.warn("Screen changing is not available because another change operation in progress.");
            return;
        }

        try {
            this.screenChangeIsLocked.set(true);;
            GameScreen oldScreen = this.getScreenAsType(GameScreen.class);
            GameScreen newGameScreen = this.screenFactory.createGameScreen(renderer, updater);

            if (oldScreen == null) {
                this.game.setScreen(newGameScreen);
                this.screenChangeIsLocked.set(false);
            } else {
                oldScreen.fadeOutScreen(() -> {
                    this.game.setScreen(newGameScreen);
                    oldScreen.dispose();
                    this.screenChangeIsLocked.set(false);
                });
            }
        } catch (Exception e) {
            log.error(e);
            this.screenChangeIsLocked.set(false);
        }
    }

    /**
     * Gets the screen as one of child type.
     *
     * @param resultType - the type that will returns.
     * @return the resultType instance or null;
     */
    private  <T extends Screen> T getScreenAsType(Class<T> resultType) {
        Screen screen = this.game.getScreen();
        if (!this.isInstanceOf(resultType, screen)) {
            return null;
        }

        return resultType.cast(screen);
    }

    private <T> boolean isInstanceOf(Class<T> clazz, Screen screen) {
        return clazz.isInstance(screen);
    }
}
