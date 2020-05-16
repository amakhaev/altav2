package com.alta_v2.game.gamelogic.interaction

import com.alta_v2.game.gamelogic.data.interaction.toModels
import com.alta_v2.game.gamelogic.data.map.MapModel
import com.alta_v2.game.gamelogic.data.npc.NpcModel
import com.alta_v2.game.gamelogic.domain.dialog.DialogProcessor
import com.alta_v2.game.gamelogic.domain.interaction.InteractionDaoService
import com.alta_v2.game.gamelogic.domain.map.MapProcessor
import com.alta_v2.game.gamelogic.domain.npc.NpcManager
import com.alta_v2.game.gamelogic.interaction.executor.InteractionExecutor
import com.alta_v2.game.gamelogic.stage.event.ChangeMapStageEvent
import com.alta_v2.mediator.serde.ActionType
import com.google.inject.assistedinject.Assisted
import com.google.inject.assistedinject.AssistedInject
import mu.KotlinLogging
import java.util.concurrent.CompletableFuture
import java.util.concurrent.atomic.AtomicBoolean

class InteractionManagerImpl @AssistedInject constructor(@Assisted private val mapModel: MapModel,
                                                         @Assisted private val npcList: List<NpcModel>,
                                                         private val interactionExecutor: InteractionExecutor,
                                                         private val interactionDaoService: InteractionDaoService,
                                                         private val npcManager: NpcManager,
                                                         private val dialogProcessor: DialogProcessor,
                                                         private val mapProcessor: MapProcessor) : InteractionManager {

    private val log = KotlinLogging.logger {  }

    private val changeMapFuture = CompletableFuture<ChangeMapStageEvent>()
    private val isAnyInteractionRun: AtomicBoolean = AtomicBoolean(false)

    init {
        npcList.forEach { npcManager.addToRandomMovement(it) }
        npcManager.startMovementProcessing()
    }

    override fun getChangeMapFuture(): CompletableFuture<ChangeMapStageEvent> = changeMapFuture

    override fun onInitialized() = dialogProcessor.showTitle(mapModel.displayName)

    @Synchronized
    override fun onRepeatableAction(action: ActionType) {
        when (action) {
            ActionType.MOVE_UP,
            ActionType.MOVE_DOWN,
            ActionType.MOVE_LEFT,
            ActionType.MOVE_RIGHT -> {
                // Movement of player available only if no interactions that are running in current moment
                if (!isAnyInteractionRun.get()) {
                    ActionType.getMovementDirection(action).run { mapProcessor.movePlayer(this) }
                }
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

    private fun handleNextButtonPressed() =
            if (isAnyInteractionRun.get()) {
                interactionExecutor.makeNextStep()
            } else {
                executeInteraction()
            }

    private fun handleBackButtonPressed() {
        dialogProcessor.hideAllDialogs()
        changeMapFuture.complete(ChangeMapStageEvent())
    }

    private fun executeInteraction() {
        val targetId = mapProcessor.findPurposeTargetedByPlayer() ?: return
        val interactionGroupId = npcList.find { it.id == targetId }?.interactionGroupId ?: return

        mapProcessor.performFocusNpcOnPlayer(targetId)
        npcManager.freezeMovement(targetId)

        val definitionModels = interactionDaoService.getInteractions(interactionGroupId)

        val result = interactionExecutor.prepare(definitionModels.toModels())
        result.thenRun {
            isAnyInteractionRun.set(false)
            npcManager.resumeMovement(targetId)
            log.debug("Interaction group $interactionGroupId has completed")
        }
        interactionExecutor.execute()
        isAnyInteractionRun.set(true)
    }
}