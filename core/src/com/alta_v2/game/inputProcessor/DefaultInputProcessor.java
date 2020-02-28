package com.alta_v2.game.inputProcessor;

import com.alta_v2.mediatorModule.serde.ActionListener;
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

    private final ActionListener actionListener;

    /**
     * Initialize new instance of {@link DefaultInputProcessor}.
     */
    @AssistedInject
    public DefaultInputProcessor(@Assisted ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean keyDown (int keycode) {
        ActionListener.ActionType actionType = ActionListener.resolveAction(keycode);
        if (actionType == null) {
            log.debug("Unknown type of action by key code {}", keycode);
            return false;
        }

        this.actionListener.onActionBegin(actionType);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean keyUp (int keycode) {
        if (keycode == 41) { // M
            log.info("Java heap size: " + Gdx.app.getJavaHeap() / 1024 / 1024 + " MB");
            log.info("Native heap size: " + Gdx.app.getNativeHeap() / 1024 / 1024 + " MB");
        }

        ActionListener.ActionType actionType = ActionListener.resolveAction(keycode);
        if (actionType == null) {
            log.debug("Unknown type of action by key code {}", keycode);
            return false;
        }

        this.actionListener.onActionFinish(actionType);
        return true;
    }
}
