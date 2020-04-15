package com.alta_v2.facade.tiledMapApi

import com.alta_v2.mediator.ProcessMediator
import com.alta_v2.physics.TiledMapPhysicEngine
import com.alta_v2.physics.task.MovementDirection
import com.alta_v2.physics.task.resultObserver.TaskResult
import com.google.inject.Inject
import mu.KotlinLogging
import java.lang.ClassCastException

class TiledMapCoreApiImpl @Inject constructor(private val processMediator: ProcessMediator) : TiledMapCoreApi {

    private val log = KotlinLogging.logger {  }

    /**
     * {@inheritDoc}
     */
    override val playerPurpose: Int?
        get() {
            return try {
                val physicEngine = getEngineFromContext()
                physicEngine.playerPurpose
            } catch (e: Exception) {
                log.error("Failed to get player purpose: ${e.message}")
                null
            }
        }

    /**
     * {@inheritDoc}
     */
    override fun performPlayerMovement(direction: MovementDirection): TaskResult? =
            try {
                val physicEngine = getEngineFromContext()
                physicEngine.performPlayerMovement(direction)
            } catch (e: Exception) {
                log.error("Failed to perform player movement: ${e.message}")
                null
            }


    /**
     * {@inheritDoc}
     */
    override fun performNpcMovement(npcId: Int, direction: MovementDirection): TaskResult? =
            try {
                val physicEngine = getEngineFromContext()
                physicEngine.performNpcMovement(npcId, direction)
            } catch (e: Exception) {
                log.error("Failed to perform NPC movement: ${e.message}")
                null
            }


    private fun getEngineFromContext(): TiledMapPhysicEngine =
            if (processMediator.getCurrentContext()?.physicEngine is TiledMapPhysicEngine) {
                processMediator.getCurrentContext()?.physicEngine as TiledMapPhysicEngine
            } else {
                throw ClassCastException("Current screen has invalid type, required TiledMapScreen")
            }

}