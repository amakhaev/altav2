package com.alta_v2.mediatorModule;

import com.alta_v2.game.ScreenManager;
import com.alta_v2.renderingModule.ScreenFactory;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Provides the mediator that responsible for orchestration of game.
 */
public class ProcessMediatorImpl implements ProcessMediator {

    private final ScreenManager screenManager;
    private final ScreenFactory screenFactory;

    /**
     * Initialize new instance of {@link ProcessMediatorImpl}.
     *
     * @param screenFactory - the {@link ScreenFactory} instance.
     * @param screenManager - the {@link ScreenManager} instance.
     */
    @AssistedInject
    public ProcessMediatorImpl(ScreenFactory screenFactory, @Assisted ScreenManager screenManager) {
        this.screenManager = screenManager;
        this.screenFactory = screenFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadMenuScreen() {
        this.screenManager.changeScreen(this.screenFactory.createMenuScreen());
    }
}
