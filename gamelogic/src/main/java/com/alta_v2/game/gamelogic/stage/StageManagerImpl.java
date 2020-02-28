package com.alta_v2.game.gamelogic.stage;

import com.alta_v2.facade.coreApi.CoreApi;
import com.alta_v2.game.gamelogic.stage.event.ChangeScreenEvent;
import com.alta_v2.mediatorModule.serde.ActionListener;
import com.google.inject.Inject;
import lombok.extern.log4j.Log4j2;

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

    private Stage createMenuStage() {
        Stage stage = this.stageFactory.createMenuStage();
        stage.subscribeToChangeScreen(this::onScreenChange);
        stage.subscribeToChangeMap(this::onMapChange);
        return stage;
    }

    private Stage createMapStage() {
        Stage stage = this.stageFactory.createMapStage();
        stage.subscribeToChangeScreen(this::onScreenChange);
        stage.subscribeToChangeMap(this::onMapChange);
        return stage;
    }

    private void onScreenChange(ChangeScreenEvent data) {
        this.currentStage.destroy();
        this.currentStage = null;
        switch (data.getTargetStageType()) {
            case MENU:
                this.coreApi.loadMenuScreen();
                this.currentStage = createMenuStage();
                break;
            case MAP:
                this.coreApi.loadTiledMapScreen();
                this.currentStage = createMapStage();
                break;
            default:
                log.error("Target stage has invalid value: {}", data.getTargetStageType());
        }
    }

    private void onMapChange(Void data) {
        log.info("Not implemented yet");
    }
}
