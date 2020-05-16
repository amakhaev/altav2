package com.alta_v2.facade.tiledMapApi

import com.alta_v2.physics.task.MovementDirection
import com.alta_v2.physics.task.resultObserver.TaskResult

interface TiledMapCoreApi {
    /**
     * Gets the ID of object which is target of player.
     */
    val playerPurpose: Int?

    /**
     * Tries to perform movement of player.
     *
     * @param direction - the direction of movement to be perform.
     */
    fun performPlayerMovement(direction: MovementDirection): TaskResult?

    /**
     * Tries to perform movement of NPC with given ID.
     *
     * @param npcId     - the id of NPC to be moved.
     * @param direction - the direction of movement to be perform.
     */
    fun performNpcMovement(npcId: Int, direction: MovementDirection): TaskResult?

    /**
     * Performs focusing of NPC with given id to player.
     */
    fun performFocusNpcOnPlayer(npcId: Int): TaskResult?
}