package com.alta_v2.game.gamelogic.stage

import com.alta_v2.exception.ChangeScreenException
import com.alta_v2.game.gamelogic.data.map.MapModel
import com.alta_v2.game.gamelogic.data.npc.toNpcModels
import com.alta_v2.game.gamelogic.domain.screen.ScreenProcessor
import com.alta_v2.game.gamelogic.stage.event.*
import com.alta_v2.game.utils.ChangeScreenResult
import com.alta_v2.mediator.serde.ActionListener
import com.alta_v2.model.NpcDefinitionModel
import com.google.inject.Inject
import mu.KotlinLogging

class StageManagerImpl @Inject constructor(private val stageFactory: StageFactory,
                                           private val screenProcessor: ScreenProcessor) : StageManager {

    private val log = KotlinLogging.logger {  }

    private var currentStage: Stage
    private var currentResult: ChangeScreenResult? = null

    init {
        currentStage = createMenuStage()
    }

    override fun getCurrentListener(): ActionListener = currentStage

    private fun onMapChange(data: Void) = log.info("Not implemented yet")

    private fun onMapScreenChange(data: ChangeStageEvent) {
        if (currentResult != null && !currentResult?.isDone!!) {
            log.warn("Failed to change map screen since another changing in progress")
            return
        }

        try {
            val event = data as ChangeMapStageEvent
            currentStage.destroy()
            currentStage = createMenuStage()

            currentResult = screenProcessor.showMenuScreen(event.mapDefinition)
            currentResult?.thenRun { currentStage.onStageLoaded() }
        } catch (e: ChangeScreenException) {
            log.error("Failed to change map screen", e)
        }
    }

    private fun onMenuScreenChange(data: ChangeStageEvent) {
        if (currentResult != null && !currentResult?.isDone!!) {
            log.warn("Failed to change map screen since another changing in progress")
            return
        }

        try {
            val event = data as ChangeMenuStageEvent
            currentStage.destroy()
            currentStage = createMapStage(
                    event.mapDefinition.displayName, event.mapDefinition.npcList
            )
            currentResult = screenProcessor.showMapScreen(event.mapDefinition)
            currentResult?.thenRun { currentStage.onStageLoaded() }
        } catch (e: ChangeScreenException) {
            log.error("Failed to change menu screen", e)
        }
    }

    private fun createMapStage(mapDisplayName: String, definitionNpcList: List<NpcDefinitionModel>): Stage {
        val stage: Stage = stageFactory.createMapStage(MapModel(mapDisplayName), definitionNpcList.toNpcModels())
        stage.subscribeToChangeScreen(createChangeScreenHandler(::onMapScreenChange))
        stage.subscribeToChangeMap(createChangeMapEventHandler(::onMapChange))
        return stage
    }

    private fun createMenuStage(): Stage {
        val stage: Stage = stageFactory.createMenuStage()
        stage.subscribeToChangeScreen(createChangeScreenHandler(::onMenuScreenChange))
        stage.subscribeToChangeMap(createChangeMapEventHandler(::onMapChange))
        return stage
    }

    private fun createChangeScreenHandler(handler: (data: ChangeStageEvent) -> Unit) =
            object : ChangeScreenHandler { override fun handle(data: ChangeStageEvent) = handler(data) }

    private fun createChangeMapEventHandler(handler: (data: Void) -> Unit) =
            object : ChangeMapEventHandler { override fun handle(data: Void) = handler(data) }
}