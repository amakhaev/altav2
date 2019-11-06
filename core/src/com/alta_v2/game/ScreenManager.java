package com.alta_v2.game;

import com.alta_v2.game.screen.GameScreen;
import com.alta_v2.game.screen.GameScreenFactory;
import com.alta_v2.renderingModule.Renderable;
import com.badlogic.gdx.Screen;
import lombok.RequiredArgsConstructor;

/**
 * Provides the manager to handle screens.
 */
@RequiredArgsConstructor
public class ScreenManager {

    private final AltaV2 game;
    private final GameScreenFactory screenFactory;

    /**
     * Changes the screen.
     *
     * @param renderable - the component to be rendered.
     */
    public void changeScreen(Renderable renderable) {
        GameScreen oldScreen = this.getScreenAsType(GameScreen.class);
        GameScreen newGameScreen = this.screenFactory.createGameScreen(renderable);

        if (oldScreen == null) {
            this.game.setScreen(newGameScreen);
        } else {
            oldScreen.fadeOutScreen(() -> {
                this.game.setScreen(newGameScreen);
                oldScreen.dispose();
            });
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
