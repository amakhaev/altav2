package com.alta_v2.mediatorModule;

import com.alta_v2.game.ScreenManager;
import com.alta_v2.game.utils.Resources;
import com.alta_v2.mediatorModule.serde.ActionControllerFactory;
import com.alta_v2.mediatorModule.serde.UpdaterFactory;
import com.alta_v2.physicsModule.TiledMapPhysicEngine;
import com.alta_v2.renderingModule.ScreenFactory;
import com.alta_v2.renderingModule.ScreenStateFactory;
import com.alta_v2.renderingModule.tiledMapScreen.TiledMapMetadata;
import com.badlogic.gdx.math.Vector2;
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
    private final ScreenStateFactory screenStateFactory;

    @Getter
    private ScreenContext currentContext;

    /**
     * Initialize new instance of {@link ProcessMediatorImpl}.
     * @param screenFactory         - the {@link ScreenFactory} instance.
     * @param screenManager         - the {@link ScreenManager} instance.
     * @param controllerFactory     - the {@link ActionControllerFactory} instance.
     * @param updaterFactory        - the {@link UpdaterFactory} instance.
     * @param screenStateFactory    - the {@link ScreenStateFactory} instance.
     */
    @AssistedInject
    public ProcessMediatorImpl(ScreenFactory screenFactory,
                               UpdaterFactory updaterFactory,
                               ActionControllerFactory controllerFactory,
                               ScreenStateFactory screenStateFactory,
                               @Assisted ScreenManager screenManager) {
        this.screenManager = screenManager;
        this.screenFactory = screenFactory;
        this.updaterFactory = updaterFactory;
        this.controllerFactory = controllerFactory;
        this.screenStateFactory = screenStateFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadMenuScreen() {
        this.currentContext = new ScreenContext(
                this.updaterFactory.createMenuScreenUpdater(),
                this.screenFactory.createMenuScreen(),
                this.controllerFactory.createMenuActionController(),
                this.screenStateFactory.createMenuState()
        );

        this.screenManager.changeScreen(this.currentContext);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadTiledMapScreen() {
        TiledMapMetadata metadata = new TiledMapMetadata(Resources.MAP_TEST, Resources.ACTOR_PERSON_12);
        TiledMapPhysicEngine physicEngine = new TiledMapPhysicEngine(new Vector2(2f, 1f), Resources.MAP_TEST);

        this.currentContext = new ScreenContext(
                this.updaterFactory.createTiledMapScreenUpdater(physicEngine),
                this.screenFactory.createTiledMapScreen(metadata),
                this.controllerFactory.createTiledMapActionController(),
                this.screenStateFactory.createTiledMapState()
        );

        this.screenManager.changeScreen(this.currentContext);
    }
}
