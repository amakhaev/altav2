package com.alta_v2.mediatorModule;

import com.alta_v2.game.ScreenManager;
import com.alta_v2.mediatorModule.updater.UpdaterFactory;
import com.alta_v2.renderingModule.ScreenFactory;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Provides the mediator that responsible for orchestration of game.
 */
public class ProcessMediatorImpl implements ProcessMediator {

    private final ScreenManager screenManager;
    private final ScreenFactory screenFactory;
    private final UpdaterFactory updaterFactory;

    /**
     * Initialize new instance of {@link ProcessMediatorImpl}.
     * @param screenFactory     - the {@link ScreenFactory} instance.
     * @param screenManager     - the {@link ScreenManager} instance.
     * @param updaterFactory    - the {@link UpdaterFactory} instance.
     */
    @AssistedInject
    public ProcessMediatorImpl(ScreenFactory screenFactory, UpdaterFactory updaterFactory, @Assisted ScreenManager screenManager) {
        this.screenManager = screenManager;
        this.screenFactory = screenFactory;
        this.updaterFactory = updaterFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadMenuScreen() {
        this.screenManager.changeScreen(
                this.screenFactory.createMenuScreen(), this.updaterFactory.createMenuScreenUpdater()
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadTiledMapScreen() {
        this.screenManager.changeScreen(
                this.screenFactory.createTiledMapScreen(), this.updaterFactory.createTiledMapScreenUpdater()
        );
    }
}
