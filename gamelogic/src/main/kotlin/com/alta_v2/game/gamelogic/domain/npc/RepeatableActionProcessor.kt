package com.alta_v2.game.gamelogic.domain.npc

import com.alta_v2.common.Destroyable
import com.alta_v2.game.gamelogic.data.npc.NpcModel

/**
 * Describes the processor of repeatable action of NPC.
 */
interface RepeatableActionProcessor : Destroyable {

    /**
     * Adds given NPC into list. Each item of list tried to perform movement to random direction time to time.
     *
     * @param npc - the [NpcModel] to be used for repeatable movement.
     */
    fun addToRandomMovement(npc: NpcModel)

    /**
     * Starts processing of random movement.
     */
    fun startAsync()

}