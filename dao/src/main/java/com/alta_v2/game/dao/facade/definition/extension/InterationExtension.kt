package com.alta_v2.game.dao.facade.definition.extension

import com.alta_v2.game.dao.data.interaction.InteractionEntity
import com.alta_v2.model.InteractionDefinitionModel

fun InteractionEntity.toDefinition() = InteractionDefinitionModel(
        id = id, effectGroupId = effectGroupId, resultGroupId = resultGroupId
)