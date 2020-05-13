package com.alta_v2.game.gamelogic.interaction

import com.alta_v2.game.gamelogic.data.map.MapModel
import com.alta_v2.game.gamelogic.data.npc.NpcModel
import com.alta_v2.game.gamelogic.domain.interaction.InteractionDaoService
import com.alta_v2.game.gamelogic.domain.map.MapProcessor
import com.alta_v2.game.gamelogic.domain.npc.NpcManager
import com.alta_v2.game.gamelogic.stage.event.ChangeMapStageEvent
import com.alta_v2.mediator.serde.ActionType
import com.google.inject.assistedinject.Assisted
import com.google.inject.assistedinject.AssistedInject
import mu.KotlinLogging
import java.util.concurrent.CompletableFuture

class InteractionManagerImpl @AssistedInject constructor(@Assisted private val mapModel: MapModel,
                                                         @Assisted private val npcList: List<NpcModel>,
                                                         private val interactionDaoService: InteractionDaoService,
                                                         private val npcManager: NpcManager,
                                                         private val mapProcessor: MapProcessor) : InteractionManager {

    private val changeMapFuture = CompletableFuture<ChangeMapStageEvent>()

    init {
        npcList.forEach { npc: NpcModel -> npcManager.addToRandomMovement(npc) }
        npcManager.startMovementProcessing()
    }

    override fun getChangeMapFuture(): CompletableFuture<ChangeMapStageEvent> = changeMapFuture

    override fun onInitialized() = mapProcessor.showTitle(mapModel.displayName)

    @Synchronized
    override fun onRepeatableAction(action: ActionType) {
        when (action) {
            ActionType.MOVE_UP,
            ActionType.MOVE_DOWN,
            ActionType.MOVE_LEFT,
            ActionType.MOVE_RIGHT -> {
                ActionType.getMovementDirection(action).run { mapProcessor.movePlayer(this) }
            }
            else -> {}
        }
    }

    @Synchronized
    override fun onPress(action: ActionType) {
        when (action) {
            ActionType.NEXT -> handleNextButtonPressed()
            ActionType.BACK -> handleBackButtonPressed()
            else -> {}
        }
    }

    override fun destroy() {
        npcManager.destroy()
    }

    private var temp = false
    private fun handleNextButtonPressed() {
        if (temp) {
            mapProcessor.hideDialog()
        } else {
            mapProcessor.showDialog("мой длинный длинный длинный длинный длинный длинный длинный длинный. длинный длинный длинный длинный длинный длинный длинный длинный супер текст")
        }
        temp = !temp
//        val targetId = mapProcessor.findPurposeTargetedByPlayer() ?: return
//        val interactionGroupId = npcList.find { it.id == targetId }?.interactionGroupId ?: return

        // interactionDaoService.getInteractions(interactionGroupId)
    }

    private fun handleBackButtonPressed() {
        mapProcessor.hideAll()
        changeMapFuture.complete(ChangeMapStageEvent())
    }
}