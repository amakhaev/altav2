package com.alta_v2.game.gamelogic.stage

import com.alta_v2.game.gamelogic.data.map.MapModel
import com.alta_v2.game.gamelogic.data.npc.NpcModel
import com.alta_v2.game.gamelogic.interaction.InteractionFactory
import com.alta_v2.game.gamelogic.interaction.InteractionManager
import com.alta_v2.game.gamelogic.utils.LogicThreadFactory
import com.alta_v2.mediator.serde.ActionType
import com.google.common.collect.Sets
import com.google.inject.assistedinject.Assisted
import com.google.inject.assistedinject.AssistedInject
import kotlinx.coroutines.*
import mu.KotlinLogging
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

private const val LISTENER_THREAD_NAME = "map-stage-listener"
private const val ACTION_THREAD_NAME = "map-stage-action-worker"

class MapStage @AssistedInject constructor(@Assisted npcList: List<NpcModel>,
                                           @Assisted mapModel: MapModel,
                                           interactionFactory: InteractionFactory) : AbstractStage() {

    private val log = KotlinLogging.logger {  }
    private val actionStatus = Sets.newConcurrentHashSet<ActionType>()
    private val interactionManager: InteractionManager = interactionFactory.createInteractionManager(mapModel, npcList)
    private val repeatableActionConsumer = {
        actionType: ActionType -> interactionManager.onRepeatableAction(actionType)
    }

    @ObsoleteCoroutinesApi
    private val actionExecutor = newFixedThreadPoolContext(5, ACTION_THREAD_NAME)
    private val scheduledExecutor = Executors.newScheduledThreadPool(1, LogicThreadFactory(LISTENER_THREAD_NAME))


    init {
        scheduledExecutor.scheduleWithFixedDelay(::handleRepeatableActions, 0L, 10L, TimeUnit.MILLISECONDS)
        interactionManager.getChangeMapFuture().thenAccept(::changeStage)
    }

    override fun onActionBegin(action: ActionType) {
        actionStatus.add(action)
    }

    @ObsoleteCoroutinesApi
    override fun onActionFinish(action: ActionType) {
        actionStatus.remove(action)

        CoroutineScope(actionExecutor).launch {
            interactionManager.onPress(action)
        }
    }

    override fun onStageLoaded() {
        interactionManager.onInitialized()
    }

    override fun destroy() {
        super.destroy()
        interactionManager.destroy()

        try {
            scheduledExecutor.shutdown()
        } catch (e: Exception) {
            log.error("Failed to shutdown scheduled executor service", e)
        }
    }

    private fun handleRepeatableActions() {
        try {
            actionStatus.isNotEmpty().run { actionStatus.forEach(repeatableActionConsumer) }
        } catch (e: Exception) {
            log.error("Failed to handle repeatable actions", e)
        }
    }
}