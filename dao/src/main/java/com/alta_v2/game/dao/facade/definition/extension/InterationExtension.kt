package com.alta_v2.game.dao.facade.definition.extension

import com.alta_v2.game.dao.data.interaction.InteractionEntity
import com.alta_v2.model.InteractionDefinitionModel
import com.alta_v2.model.UiEffectDefinitionModel

internal fun InteractionEntity.toDefinition(uiEffectModels: List<UiEffectDefinitionModel> = emptyList()) = InteractionDefinitionModel(
        id = id, effectModels = uiEffectModels
)