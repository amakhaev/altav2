package com.alta_v2.game.dao.facade.definition

import com.alta_v2.game.dao.data.person.PersonEntity
import com.alta_v2.model.NpcDefinitionModel
import com.alta_v2.model.PlayerDefinitionModel

internal fun PersonEntity.toPlayerDefinition() = PlayerDefinitionModel(
        id = personId,
        texturePath = texturePath,
        x = x.toFloat(),
        y = y.toFloat()
)

internal fun convertToNpcDefinitions(npcDefinitions: List<PersonEntity>): List<NpcDefinitionModel> =
        npcDefinitions.map { it.toNpcDefinition() }.toList()

private fun PersonEntity.toNpcDefinition() = NpcDefinitionModel(
        id = personId,
        texturePath = texturePath,
        x = x.toFloat(),
        y = y.toFloat(),
        repeatMovementInterval = movementInterval
)