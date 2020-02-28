package com.alta_v2.game;

import com.alta_v2.game.screen.GameScreen;
import com.alta_v2.game.screen.GameScreenFactory;
import com.alta_v2.mediatorModule.screen.ScreenContext;
import com.badlogic.gdx.Screen;
import com.google.inject.Inject;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Provides the manager to handle screens.
 */
@Log4j2
public class ScreenManager {

    @Setter
    private AltaV2 game;

    private final GameScreenFactory screenFactory;
    private final AtomicBoolean screenChangeIsLocked;

    /**
     * Initialize new instance of {@link ScreenManager}.
     *
     * @param screenFactory - the {@link GameScreenFactory} instance.
     */
    @Inject
    public ScreenManager(GameScreenFactory screenFactory) {
        this.screenFactory = screenFactory;
        this.screenChangeIsLocked = new AtomicBoolean(false);
    }

    /**
     * Changes the screen.
     *
     * @param screenContext - the {@link ScreenContext} instance.
     */
    public void changeScreen(ScreenContext screenContext) {
        if (this.game == null) {
            throw new NullPointerException("AltaV2 must not be null");
        }

        if (this.screenChangeIsLocked.get()) {
            log.warn("Screen changing is not available because another change operation in progress.");
            return;
        }

        try {
            this.screenChangeIsLocked.set(true);;
            GameScreen oldScreen = this.getScreenAsType(GameScreen.class);
            GameScreen newGameScreen = this.screenFactory.createGameScreen(screenContext);

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
        if (this.game == null) {
            throw new NullPointerException("AltaV2 must not be null");
        }

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
