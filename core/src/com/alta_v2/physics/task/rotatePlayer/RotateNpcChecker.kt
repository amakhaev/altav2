package com.alta_v2.physics.task.rotatePlayer

import com.alta_v2.physics.executionContext.TiledMapEngineContext
import com.alta_v2.physics.task.ExecutionChecker
import com.alta_v2.rendering.tiledMapScreen.actors.PersonView

/**
 * Provides the checker that indicates when NPC can be rotate.
 */
class RotateNpcChecker(private val npcId: Int, private val currentView: PersonView) : ExecutionChecker {
    override fun canTaskBeExecuted(context: TiledMapEngineContext): Boolean {
        val npc = context.npcMap[npcId]
        return !npc!!.view.isReserved() && npc.view.getValue() != currentView
    }
}