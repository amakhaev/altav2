package com.alta_v2.game.dao.facade.definition.extension

import com.alta_v2.game.dao.data.interaction.InteractionEntity
import com.alta_v2.model.InteractionDefinitionModel
import com.alta_v2.model.UiEffectDefinition

internal fun InteractionEntity.toDefinition(uiEffects: List<UiEffectDefinition> = emptyList()) = InteractionDefinitionModel(
        id = id, effects = uiEffects
)