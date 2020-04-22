package com.alta_v2.game.gamelogic.interaction

import com.alta_v2.game.gamelogic.data.interaction.InteractionModel

/**
 * Provides the executor for interactions
 */
interface InteractionExecutor {

    /**
     * Performs given interactions.
     */
    fun performInteractions(interactions: List<InteractionModel>): ExecutorResult

}