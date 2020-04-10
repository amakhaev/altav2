package com.alta_v2.game.gamelogic.stage;

import com.alta_v2.facade.dialogApi.DialogCoreApi;
import com.alta_v2.game.gamelogic.data.map.MapModel;
import com.alta_v2.game.gamelogic.data.npc.NpcModel;
import com.alta_v2.game.gamelogic.domain.npc.RepeatableActionProcessor;
import com.alta_v2.game.gamelogic.domain.player.PlayerProcessor;
import com.alta_v2.game.gamelogic.stage.event.ChangeMapStageEvent;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class MapStage extends AbstractStage {

    private final PlayerProcessor playerProcessor;
    private final RepeatableActionProcessor npcProcessor;
    private final DialogCoreApi dialogCoreApi;
    private final MapModel mapModel;

    @AssistedInject
    public MapStage(@Assisted MapModel mapModel,
                    @Assisted List<NpcModel> npcList,
                    PlayerProcessor playerProcessor,
                    RepeatableActionProcessor npcProcessor,
                    DialogCoreApi dialogCoreApi) {
        this.playerProcessor = playerProcessor;
        this.npcProcessor = npcProcessor;
        this.dialogCoreApi = dialogCoreApi;
        this.mapModel = mapModel;

        npcList.forEach(this.npcProcessor::addToRandomMovement);
        this.npcProcessor.startAsync();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onActionBegin(ActionType action) {
        this.playerProcessor.actionBegin(action);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onActionFinish(ActionType action) {
        this.playerProcessor.actionFinish(action);

        if (action == ActionType.BACK) {
            dialogCoreApi.hideTitleDialog();
            this.changeStage(new ChangeMapStageEvent());
        }
    }

    @Override
    public void onStageLoaded() {
        dialogCoreApi.showTitleDialog(mapModel.getDisplayName(), 3000);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
        super.destroy();
        this.playerProcessor.destroy();
        this.npcProcessor.destroy();
    }
}
