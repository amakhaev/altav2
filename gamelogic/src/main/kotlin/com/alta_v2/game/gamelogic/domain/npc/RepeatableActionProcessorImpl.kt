package com.alta_v2.game.gamelogic.domain.npc

import com.alta_v2.facade.tiledMapApi.TiledMapCoreApi
import com.alta_v2.game.gamelogic.data.npc.NpcModel
import com.alta_v2.game.gamelogic.utils.LogicThreadFactory
import com.alta_v2.physics.task.MovementDirection
import com.google.inject.Inject
import mu.KotlinLogging
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.function.Consumer

private const val LISTENER_THREAD_NAME = "repeatable-action-processing"
private val log = KotlinLogging.logger {  }

class RepeatableActionProcessorImpl @Inject constructor(val tiledMapCoreApi: TiledMapCoreApi) : RepeatableActionProcessor {

    private val npcList = CopyOnWriteArrayList<NpcModel>()
    private val npcMovementConsumer = Consumer { npc: NpcModel -> this.performNpcMovement(npc) }

    private val executorService = Executors.newScheduledThreadPool(
            1, LogicThreadFactory(LISTENER_THREAD_NAME)
    )

    override fun addToRandomMovement(npc: NpcModel) {
        npc.lastMovementMills = System.currentTimeMillis()
        npcList.add(npc)
    }

    override fun startAsync() {
        executorService.scheduleWithFixedDelay({ this.performNpcMovementForAll() }, 0L, 100L, TimeUnit.MILLISECONDS)
    }

    override fun destroy() {
        try {
            executorService.shutdown()
        } catch (e: Exception) {
            log.error("Failed to shutdown executor service", e)
        }
        npcList.clear()
    }

    private fun performNpcMovementForAll() {
        npcList.forEach(npcMovementConsumer)
    }

    private fun performNpcMovement(npc: NpcModel) {
        if (npc.isMovementRunning) {
            return
        }
        if (System.currentTimeMillis() - npc.lastMovementMills < npc.repeatMovementInterval) {
            return
        }
        val taskResult = tiledMapCoreApi.performNpcMovement(npc.id, MovementDirection.randomDirection())
        taskResult?.run {
            npc.isMovementRunning = true
            thenRun {
                npc.isMovementRunning = false
                npc.lastMovementMills = System.currentTimeMillis()
            }
        }
    }
}