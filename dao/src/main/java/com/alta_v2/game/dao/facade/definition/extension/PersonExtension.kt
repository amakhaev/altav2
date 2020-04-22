package com.alta_v2.game.dao.facade.definition.extension

import com.alta_v2.game.dao.data.person.PersonEntity
import com.alta_v2.model.NpcDefinitionModel
import com.alta_v2.model.PlayerDefinitionModel

internal fun PersonEntity.toPlayerDefinition() = PlayerDefinitionModel(
        id = personId,
        texturePath = texturePath,
        x = x.toFloat(),
        y = y.toFloat()
)

internal fun List<PersonEntity>.toNpcDefinitions() = map { it.toNpcDefinition() }

private fun PersonEntity.toNpcDefinition() = NpcDefinitionModel(
        id = personId,
        texturePath = texturePath,
        x = x.toFloat(),
        y = y.toFloat(),
        repeatMovementInterval = movementInterval,
        interactionGroupId = interactionGroupId
)