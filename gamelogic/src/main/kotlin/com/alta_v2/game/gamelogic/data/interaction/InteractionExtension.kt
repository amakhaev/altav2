package com.alta_v2.game.gamelogic.data.interaction

import com.alta_v2.model.InteractionDefinitionModel

internal fun InteractionDefinitionModel.toModel() = InteractionModel(
        id = id, effectModels = effectModels.map { it.toModel() }
)