package com.alta_v2.mediatorModule;

import com.alta_v2.game.ScreenManager;
import com.alta_v2.game.utils.Resources;
import com.alta_v2.mediatorModule.screen.ContextFactory;
import com.alta_v2.mediatorModule.screen.ScreenContext;
import com.alta_v2.model.NpcDefinitionModel;
import com.alta_v2.model.PlayerDefinitionModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Provides the mediator that responsible for orchestration of game.
 */
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ProcessMediatorImpl implements ProcessMediator {

    private final ContextFactory contextFactory;
    private final ScreenManager screenManager;

    @Getter
    private ScreenContext currentContext;

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadMenuScreen() {
        this.currentContext = this.contextFactory.createMenuContext();
        this.screenManager.changeScreen(this.currentContext);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadTiledMapScreen() {
        PlayerDefinitionModel playerDefinitionModel = this.createMockPlayer();
        List<NpcDefinitionModel> npcList = this.createMockNpcList();

        this.currentContext = this.contextFactory.createTiledMapContext(playerDefinitionModel, npcList);
        this.screenManager.changeScreen(this.currentContext);
    }

    private PlayerDefinitionModel createMockPlayer() {
        PlayerDefinitionModel playerDefinitionModel = new PlayerDefinitionModel();
        playerDefinitionModel.id = UUID.randomUUID().toString();
        playerDefinitionModel.texturePath = Resources.ACTOR_PERSON_12;
        playerDefinitionModel.x = 3f;
        playerDefinitionModel.y = 1f;
        return playerDefinitionModel;
    }

    private List<NpcDefinitionModel> createMockNpcList() {
        List<NpcDefinitionModel> npcList = new ArrayList<>();
        NpcDefinitionModel npcDefinitionModel1 = new NpcDefinitionModel();
        npcDefinitionModel1.id = UUID.randomUUID().toString();
        npcDefinitionModel1.texturePath = Resources.CHILD_1;
        npcDefinitionModel1.x = 1f;
        npcDefinitionModel1.y = 2;

        NpcDefinitionModel npcDefinitionModel2 = new NpcDefinitionModel();
        npcDefinitionModel2.id = UUID.randomUUID().toString();
        npcDefinitionModel2.texturePath = Resources.CHILD_2;
        npcDefinitionModel2.x = 8f;
        npcDefinitionModel2.y = 10f;

        npcList.add(npcDefinitionModel1);
        npcList.add(npcDefinitionModel2);
        return npcList;
    }
}
