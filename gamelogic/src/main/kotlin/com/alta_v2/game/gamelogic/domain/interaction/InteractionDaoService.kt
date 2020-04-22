package com.alta_v2.game.gamelogic.domain.interaction

import com.alta_v2.model.InteractionDefinitionModel

/**
 * Provides service to communicate with DAO layer related to interactions.
 */
interface InteractionDaoService {

    /**
     * Gets list of interaction by given ID of interaction group
     */
    fun getInteractions(groupId: Int): List<InteractionDefinitionModel>

}