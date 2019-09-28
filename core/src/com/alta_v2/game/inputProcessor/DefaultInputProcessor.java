package com.alta_v2.game.inputProcessor;

import com.alta_v2.game.ScreenSwitcher;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import lombok.extern.log4j.Log4j2;

/**
 * Provides the default input processor.
 */
@Log4j2
public class DefaultInputProcessor extends InputAdapter {

    private final ScreenSwitcher screenSwitcher;

    /**
     * Initialize new instance of {@link DefaultInputProcessor}.
     *
     * @param screenSwitcher - the {@link ScreenSwitcher} instance.
     */
    @AssistedInject
    public DefaultInputProcessor(@Assisted ScreenSwitcher screenSwitcher) {
        this.screenSwitcher = screenSwitcher;
    }

    @Override
    public boolean keyTyped (char character) {
        if (character == 'm') {
            log.info("Java heap size: " + Gdx.app.getJavaHeap() / 1024 / 1024 + " MB");
            log.info("Native heap size: " + Gdx.app.getNativeHeap() / 1024 / 1024 + " MB");
        }

        if (character == 'c') {
            this.screenSwitcher.changeTiledMap();
        }
        return false;
    }

}
