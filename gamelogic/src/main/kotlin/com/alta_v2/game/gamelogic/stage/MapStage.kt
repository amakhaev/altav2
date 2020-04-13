package com.alta_v2.game.gamelogic.stage

import com.alta_v2.facade.dialogApi.DialogCoreApi
import com.alta_v2.game.gamelogic.data.map.MapModel
import com.alta_v2.game.gamelogic.data.npc.NpcModel
import com.alta_v2.game.gamelogic.domain.npc.RepeatableActionProcessor
import com.alta_v2.game.gamelogic.domain.player.PlayerProcessor
import com.alta_v2.game.gamelogic.stage.event.ChangeMapStageEvent
import com.alta_v2.mediator.serde.ActionListener
import com.alta_v2.mediator.serde.ActionListener.ActionType
import com.google.inject.assistedinject.Assisted
import com.google.inject.assistedinject.AssistedInject
import java.util.function.Consumer

class MapStage @AssistedInject constructor(@Assisted npcList: List<NpcModel>,
                                           @Assisted private val mapModel: MapModel,
                                           private val playerProcessor: PlayerProcessor,
                                           private val npcProcessor: RepeatableActionProcessor,
                                           private val dialogCoreApi: DialogCoreApi) : AbstractStage() {

    init {
        npcList.forEach(Consumer { npc: NpcModel -> npcProcessor.addToRandomMovement(npc) })
        npcProcessor.startAsync()
    }

    override fun onActionBegin(action: ActionListener.ActionType?) {
        playerProcessor.actionBegin(action!!)
    }

    override fun onActionFinish(action: ActionListener.ActionType?) {
        playerProcessor.actionFinish(action!!)

        if (action == ActionType.BACK) {
            dialogCoreApi.hideTitleDialog()
            changeStage(ChangeMapStageEvent())
        }
    }

    override fun onStageLoaded() {
        dialogCoreApi.showTitleDialog(mapModel.displayName, 3000f)
    }

    override fun destroy() {
        super.destroy()
        playerProcessor.destroy()
        npcProcessor.destroy()
    }
}