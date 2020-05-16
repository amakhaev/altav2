package com.alta_v2.game.gamelogic.domain.map

import com.alta_v2.facade.tiledMapApi.TiledMapCoreApi
import com.alta_v2.physics.task.MovementDirection
import com.alta_v2.physics.task.resultObserver.TaskResult
import com.google.inject.Inject

class MapProcessorImpl @Inject constructor(private val tiledMapCoreApi: TiledMapCoreApi) : MapProcessor {

    override fun movePlayer(direction: MovementDirection) = tiledMapCoreApi.performPlayerMovement(direction)

    override fun moveNpc(npcId: Int, direction: MovementDirection) = tiledMapCoreApi.performNpcMovement(npcId, direction)

    override fun findPurposeTargetedByPlayer(): Int? = tiledMapCoreApi.playerPurpose

    override fun performFocusNpcOnPlayer(npcId: Int): TaskResult? = tiledMapCoreApi.performFocusNpcOnPlayer(npcId)
}