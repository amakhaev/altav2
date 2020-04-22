package com.alta_v2.game.gamelogic.data.npc

import com.alta_v2.model.NpcDefinitionModel

fun List<NpcDefinitionModel>.toNpcModels() = map { it.toNpcModel() }.toList()

fun NpcDefinitionModel.toNpcModel() = NpcModel(
        id =id,
        localX = x,
        localY = y,
        repeatMovementInterval = repeatMovementInterval,
        interactionGroupId = interactionGroupId
)