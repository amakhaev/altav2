package com.alta_v2.game.gamelogic.domain.player

import com.alta_v2.facade.tiledMapApi.TiledMapCoreApi
import com.alta_v2.game.gamelogic.utils.LogicThreadFactory
import com.alta_v2.mediator.serde.ActionListener.ActionType
import com.google.common.collect.Sets
import com.google.inject.Inject
import mu.KotlinLogging
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.function.Consumer

private const val LISTENER_THREAD_NAME = "map-stage-listener"
private val log = KotlinLogging.logger {  }

class PlayerProcessorImpl @Inject constructor(private val tiledMapCoreApi: TiledMapCoreApi) : PlayerProcessor {

    private val actionStatus = Sets.newConcurrentHashSet<ActionType>()
    private val statusConsumer = Consumer { actionType: ActionType -> this.resolveAction(actionType) }

    private val executorService = Executors.newScheduledThreadPool(
            1, LogicThreadFactory(LISTENER_THREAD_NAME)
    )

    init {
        executorService.scheduleWithFixedDelay({ this.checkActions() }, 0L, 10L, TimeUnit.MILLISECONDS)
    }

    override fun actionBegin(action: ActionType) {
        actionStatus.add(action)
    }

    override fun actionFinish(action: ActionType) {
        actionStatus.remove(action)
    }

    override fun destroy() {
        try {
            executorService.shutdown()
        } catch (e: Exception) {
            log.error("Failed to shutdown executor service", e)
        }
    }

    private fun checkActions() {
        try {
            if (actionStatus.isEmpty()) {
                return
            }
            actionStatus.forEach(statusConsumer)
        } catch (e: Exception) {
            log.error("Failed to check actions", e)
        }
    }

    private fun resolveAction(actionType: ActionType) {
        when (actionType) {
            ActionType.MOVE_UP,
            ActionType.MOVE_DOWN,
            ActionType.MOVE_LEFT,
            ActionType.MOVE_RIGHT -> {
                val type = ActionType.getMovementDirection(actionType)
                if (type != null) {
                    tiledMapCoreApi.performPlayerMovement(type)
                }
            }
            else -> {}
        }
    }
}