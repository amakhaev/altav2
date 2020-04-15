package com.alta_v2.physics.task.moveNpc

import com.alta_v2.physics.executionContext.TiledMapEngineContext
import com.alta_v2.physics.task.ExecutionChecker
import mu.KotlinLogging

class MoveNpcChecker(private val npcId: Int) : ExecutionChecker {

    private val log = KotlinLogging.logger {  }

    override fun canTaskBeExecuted(context: TiledMapEngineContext): Boolean {
        if (!context.npcMap.containsKey(npcId)) {
            log.error("Given npcId: {} not found in context", npcId)
            return false
        }
        val npc = context.npcMap[npcId]
        return !npc!!.isReserved() && !npc.globalPoint.isReserved() && !npc.localPoint.isReserved()
    }
}