package com.alta_v2.game.gamelogic.stage;

import com.alta_v2.game.gamelogic.stage.event.ChangeMapStageEvent;
import com.alta_v2.game.gamelogic.stage.event.ChangeMenuStageEvent;
import com.alta_v2.game.utils.Resources;
import com.alta_v2.model.NpcDefinitionModel;
import com.alta_v2.model.PlayerDefinitionModel;
import com.alta_v2.model.TiledMapDefinitionModel;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Log4j2
public class MenuStage extends AbstractStage {

    /**
     * {@inheritDoc}
     */
    @Override
    public void onActionBegin(ActionType action) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onActionFinish(ActionType action) {
        if (action != ActionType.BACK) {
            return;
        }

        ChangeMenuStageEvent event = new ChangeMenuStageEvent(
                TiledMapDefinitionModel.builder()
                        .mapPath(Resources.MAP_TEST)
                        .player(this.createMockPlayer())
                        .npcList(this.createMockNpcList())
                        .build()
        );
        this.changeStage(event);
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
        npcDefinitionModel1.y = 2f;
        npcDefinitionModel1.repeatMovementInterval = 3000;

        NpcDefinitionModel npcDefinitionModel2 = new NpcDefinitionModel();
        npcDefinitionModel2.id = UUID.randomUUID().toString();
        npcDefinitionModel2.texturePath = Resources.CHILD_2;
        npcDefinitionModel2.x = 8f;
        npcDefinitionModel2.y = 10f;
        npcDefinitionModel2.repeatMovementInterval = 5000;

        npcList.add(npcDefinitionModel1);
        npcList.add(npcDefinitionModel2);
        return npcList;
    }
}
