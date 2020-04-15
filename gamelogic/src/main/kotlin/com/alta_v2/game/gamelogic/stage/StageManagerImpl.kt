package com.alta_v2.game.gamelogic.stage

import com.alta_v2.exception.ChangeScreenException
import com.alta_v2.facade.coreApi.ScreenCoreApi
import com.alta_v2.game.gamelogic.data.map.MapModel
import com.alta_v2.game.gamelogic.data.npc.NpcModel
import com.alta_v2.game.gamelogic.stage.event.*
import com.alta_v2.game.utils.ChangeScreenResult
import com.alta_v2.mediator.serde.ActionListener
import com.alta_v2.model.NpcDefinitionModel
import com.google.inject.Inject
import mu.KotlinLogging
import java.util.stream.Collectors

private val log = KotlinLogging.logger {  }

class StageManagerImpl @Inject constructor(private val stageFactory: StageFactory,
                                           private val screenCoreApi: ScreenCoreApi) : StageManager {

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

            currentResult = screenCoreApi.loadMenuScreen(event.mapDefinition)
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
            currentResult = screenCoreApi.loadTiledMapScreen(event.mapDefinition)
            currentResult?.thenRun { currentStage.onStageLoaded() }
        } catch (e: ChangeScreenException) {
            log.error("Failed to change menu screen", e)
        }
    }

    private fun createMapStage(mapDisplayName: String, definitionNpcList: List<NpcDefinitionModel>): Stage {
        val npcModels = definitionNpcList.stream()
                .map { npcDefinition: NpcDefinitionModel ->
                    NpcModel(
                            npcDefinition.id, npcDefinition.x, npcDefinition.y, npcDefinition.repeatMovementInterval
                    )
                }
                .collect(Collectors.toList())
        val mapModel = MapModel(mapDisplayName)
        val stage: Stage = stageFactory.createMapStage(mapModel, npcModels)

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