package com.alta_v2.game.gamelogic.data.interaction

import com.alta_v2.model.InteractionDefinitionModel

fun List<InteractionDefinitionModel>.toModels() = map { it.toModel() }

private fun InteractionDefinitionModel.toModel() = InteractionModel(
        id = id, effectModels = effectModels.map { it.toModel() }
)