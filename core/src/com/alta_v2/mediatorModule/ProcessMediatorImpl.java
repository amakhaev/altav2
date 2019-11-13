package com.alta_v2.mediatorModule;

import com.alta_v2.game.ScreenManager;
import com.alta_v2.mediatorModule.actionController.ActionControllerFactory;
import com.alta_v2.mediatorModule.updater.UpdaterFactory;
import com.alta_v2.renderingModule.ScreenFactory;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import lombok.Getter;

/**
 * Provides the mediator that responsible for orchestration of game.
 */
public class ProcessMediatorImpl implements ProcessMediator {

    private final ScreenManager screenManager;
    private final ScreenFactory screenFactory;
    private final UpdaterFactory updaterFactory;
    private final ActionControllerFactory controllerFactory;

    @Getter
    private ScreenContext currentContext;

    /**
     * Initialize new instance of {@link ProcessMediatorImpl}.
     * @param screenFactory     - the {@link ScreenFactory} instance.
     * @param screenManager     - the {@link ScreenManager} instance.
     * @param controllerFactory - the {@link ActionControllerFactory} instance.
     * @param updaterFactory    - the {@link UpdaterFactory} instance.
     */
    @AssistedInject
    public ProcessMediatorImpl(ScreenFactory screenFactory,
                               UpdaterFactory updaterFactory,
                               ActionControllerFactory controllerFactory,
                               @Assisted ScreenManager screenManager) {
        this.screenManager = screenManager;
        this.screenFactory = screenFactory;
        this.updaterFactory = updaterFactory;
        this.controllerFactory = controllerFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadMenuScreen() {
        this.currentContext = new ScreenContext(
                this.updaterFactory.createMenuScreenUpdater(),
                this.screenFactory.createMenuScreen(),
                this.controllerFactory.createTiledMapActionController()
        );

        this.screenManager.changeScreen(this.currentContext);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadTiledMapScreen() {
        this.currentContext = new ScreenContext(
                this.updaterFactory.createTiledMapScreenUpdater(),
                this.screenFactory.createTiledMapScreen(),
                this.controllerFactory.createTiledMapActionController()
        );

        this.screenManager.changeScreen(this.currentContext);
    }
}
