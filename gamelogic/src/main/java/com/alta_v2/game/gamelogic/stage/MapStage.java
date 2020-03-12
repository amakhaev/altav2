package com.alta_v2.game.gamelogic.stage;

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

    @AssistedInject
    public MapStage(PlayerProcessor playerProcessor,
                    RepeatableActionProcessor npcProcessor,
                    @Assisted List<NpcModel> npcList) {
        this.playerProcessor = playerProcessor;
        this.npcProcessor = npcProcessor;
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
            this.changeStage(new ChangeMapStageEvent());
        }
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
