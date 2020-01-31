package com.alta_v2.mediatorModule;

import com.alta_v2.game.ScreenManager;
import com.alta_v2.game.utils.Resources;
import com.alta_v2.mediatorModule.serde.ActionControllerFactory;
import com.alta_v2.mediatorModule.serde.UpdaterFactory;
import com.alta_v2.model.NpcModel;
import com.alta_v2.model.PlayerModel;
import com.alta_v2.physicsModule.TiledMapPhysicEngine;
import com.alta_v2.renderingModule.ScreenFactory;
import com.alta_v2.renderingModule.ScreenStateFactory;
import com.alta_v2.renderingModule.tiledMapScreen.TiledMapMetadata;
import com.badlogic.gdx.math.Vector2;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
        PlayerModel playerModel = this.createMockPlayer();
        List<NpcModel> npcList = this.createMockNpcList();

        TiledMapMetadata metadata = new TiledMapMetadata(
                Resources.MAP_TEST,
                playerModel.texturePath,
                npcList.stream().collect(Collectors.toMap(n -> n.id, n -> n.texturePath))
        );
        TiledMapPhysicEngine physicEngine = new TiledMapPhysicEngine(
                new Vector2(playerModel.x, playerModel.y), Resources.MAP_TEST, playerModel.id
        );

        this.currentContext = new ScreenContext(
                this.updaterFactory.createTiledMapScreenUpdater(physicEngine),
                this.screenFactory.createTiledMapScreen(metadata),
                this.controllerFactory.createTiledMapActionController(physicEngine),
                this.screenStateFactory.createTiledMapState()
        );

        this.screenManager.changeScreen(this.currentContext);
    }

    private PlayerModel createMockPlayer() {
        PlayerModel playerModel = new PlayerModel();
        playerModel.id = UUID.randomUUID().toString();
        playerModel.texturePath = Resources.ACTOR_PERSON_12;
        playerModel.x = 3f;
        playerModel.y = 1f;
        return playerModel;
    }

    private List<NpcModel> createMockNpcList() {
        List<NpcModel> npcList = new ArrayList<>();
        NpcModel npcModel1 = new NpcModel();
        npcModel1.id = UUID.randomUUID().toString();
        npcModel1.texturePath = Resources.CHILD_1;
        npcModel1.x = 3f;
        npcModel1.y = 10f;

        NpcModel npcModel2 = new NpcModel();
        npcModel2.id = UUID.randomUUID().toString();
        npcModel2.texturePath = Resources.CHILD_2;
        npcModel2.x = 8f;
        npcModel2.y = 10f;

        npcList.add(npcModel1);
        npcList.add(npcModel2);
        return npcList;
    }
}
