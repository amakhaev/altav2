package com.alta_v2.game.inputProcessor;

import com.alta_v2.mediatorModule.ProcessMediator;
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

    private final ProcessMediator processMediator;

    /**
     * Initialize new instance of {@link DefaultInputProcessor}.
     *
     * @param processMediator - the {@link ProcessMediator} instance.
     */
    @AssistedInject
    public DefaultInputProcessor(@Assisted ProcessMediator processMediator) {
        this.processMediator = processMediator;
    }

    @Override
    public boolean keyTyped (char character) {
        if (character == 'm') {
            log.info("Java heap size: " + Gdx.app.getJavaHeap() / 1024 / 1024 + " MB");
            log.info("Native heap size: " + Gdx.app.getNativeHeap() / 1024 / 1024 + " MB");
        }

        if (character == '1') {
            this.processMediator.loadMenuScreen();
        } else if (character == '2') {
            this.processMediator.loadTiledMapScreen();
        }
        return false;
    }

}
