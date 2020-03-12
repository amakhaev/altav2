package com.alta_v2.game.gamelogic.stage;

import com.alta_v2.facade.coreApi.CoreApi;
import com.alta_v2.game.gamelogic.data.npc.NpcModel;
import com.alta_v2.game.gamelogic.stage.event.ChangeMapStageEvent;
import com.alta_v2.game.gamelogic.stage.event.ChangeMenuStageEvent;
import com.alta_v2.game.gamelogic.stage.event.ChangeStageEvent;
import com.alta_v2.mediator.serde.ActionListener;
import com.alta_v2.model.NpcDefinitionModel;
import com.google.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class StageManagerImpl implements StageManager {

    private final StageFactory stageFactory;
    private final CoreApi coreApi;

    private Stage currentStage;

    @Inject
    public StageManagerImpl(StageFactory stageFactory, CoreApi coreApi) {
        this.stageFactory = stageFactory;
        this.coreApi = coreApi;
        this.currentStage = this.createMenuStage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionListener getCurrentListener() {
        return this.currentStage;
    }

    private void onMapScreenChange(ChangeStageEvent data) {
        try {
            ChangeMapStageEvent event = ChangeStageEvent.resolve(data, ChangeMapStageEvent.class);
            this.currentStage.destroy();
            this.currentStage = null;
            this.coreApi.loadMenuScreen(event.getMapDefinition());
            this.currentStage = createMenuStage();
        } catch (NullPointerException | ClassCastException e) {
            log.error("Failed to change map screen", e);
        }
    }

    private void onMenuScreenChange(ChangeStageEvent data) {
        try {
            ChangeMenuStageEvent event = ChangeStageEvent.resolve(data, ChangeMenuStageEvent.class);
            this.currentStage.destroy();
            this.currentStage = null;
            this.coreApi.loadTiledMapScreen(event.getMapDefinition());
            this.currentStage = createMapStage(event.getMapDefinition().getNpcList());
        } catch (NullPointerException | ClassCastException e) {
            log.error("Failed to change menu screen", e);
        }
    }

    private void onMapChange(Void data) {
        log.info("Not implemented yet");
    }

    private Stage createMenuStage() {
        Stage stage = this.stageFactory.createMenuStage();
        stage.subscribeToChangeScreen(this::onMenuScreenChange);
        stage.subscribeToChangeMap(this::onMapChange);
        return stage;
    }

    private Stage createMapStage(List<NpcDefinitionModel> definitionNpcList) {
        List<NpcModel> npcModels = definitionNpcList.stream()
                .map(npcDefinition -> NpcModel.builder().id(npcDefinition.id).localX(npcDefinition.x).localY(npcDefinition.y).build())
                .collect(Collectors.toList());

        Stage stage = this.stageFactory.createMapStage(npcModels);
        stage.subscribeToChangeScreen(this::onMapScreenChange);
        stage.subscribeToChangeMap(this::onMapChange);
        return stage;
    }
}
