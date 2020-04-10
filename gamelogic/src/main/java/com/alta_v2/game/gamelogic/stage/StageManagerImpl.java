package com.alta_v2.game.gamelogic.stage;

import com.alta_v2.facade.coreApi.ScreenCoreApi;
import com.alta_v2.game.gamelogic.data.map.MapModel;
import com.alta_v2.game.gamelogic.data.npc.NpcModel;
import com.alta_v2.game.gamelogic.stage.event.ChangeMapStageEvent;
import com.alta_v2.game.gamelogic.stage.event.ChangeMenuStageEvent;
import com.alta_v2.game.gamelogic.stage.event.ChangeStageEvent;
import com.alta_v2.game.utils.ChangeScreenResult;
import com.alta_v2.mediator.serde.ActionListener;
import com.alta_v2.model.NpcDefinitionModel;
import com.google.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class StageManagerImpl implements StageManager {

    private final StageFactory stageFactory;
    private final ScreenCoreApi screenCoreApi;

    private Stage currentStage;

    @Inject
    public StageManagerImpl(StageFactory stageFactory, ScreenCoreApi screenCoreApi) {
        this.stageFactory = stageFactory;
        this.screenCoreApi = screenCoreApi;
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
            currentStage.destroy();
            currentStage = createMenuStage();
            ChangeScreenResult loadResult = screenCoreApi.loadMenuScreen(event.getMapDefinition());
            if (loadResult != null) {
                loadResult.thenRun(currentStage::onStageLoaded);
            }
        } catch (NullPointerException | ClassCastException e) {
            log.error("Failed to change map screen", e);
        }
    }

    private void onMenuScreenChange(ChangeStageEvent data) {
        try {
            ChangeMenuStageEvent event = ChangeStageEvent.resolve(data, ChangeMenuStageEvent.class);
            currentStage.destroy();
            currentStage = createMapStage(
                    event.getMapDefinition().getDisplayName(), event.getMapDefinition().getNpcList()
            );
            ChangeScreenResult loadResult = screenCoreApi.loadTiledMapScreen(event.getMapDefinition());
            if (loadResult != null) {
                loadResult.thenRun(currentStage::onStageLoaded);
            }
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

    private Stage createMapStage(String mapDisplayName, List<NpcDefinitionModel> definitionNpcList) {
        List<NpcModel> npcModels = definitionNpcList.stream()
                .map(npcDefinition ->
                        NpcModel.builder()
                                .id(npcDefinition.getId())
                                .localX(npcDefinition.getX())
                                .localY(npcDefinition.getY())
                                .repeatMovementInterval(npcDefinition.getRepeatMovementInterval())
                                .build()
                )
                .collect(Collectors.toList());

        MapModel mapModel = MapModel.builder().displayName(mapDisplayName).build();

        Stage stage = this.stageFactory.createMapStage(mapModel, npcModels);
        stage.subscribeToChangeScreen(this::onMapScreenChange);
        stage.subscribeToChangeMap(this::onMapChange);
        return stage;
    }
}
