package com.alta_v2.game.gamelogic.domain.interaction

import com.alta_v2.game.dao.facade.definition.DefinitionDaoApi
import com.alta_v2.model.InteractionDefinitionModel
import com.google.inject.Inject

class InteractionDaoServiceImpl @Inject constructor(private val definitionDaoApi: DefinitionDaoApi) : InteractionDaoService {

    override fun getInteractions(groupId: Int): List<InteractionDefinitionModel> =
            definitionDaoApi.getInteractionDefinitions(groupId)
}