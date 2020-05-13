package com.alta_v2.game.gamelogic.data.interaction

import com.alta_v2.model.*

internal fun UiEffectDefinitionModel.toModel() = EffectModel(
        effectId = id,
        movementEffect = movementEffectModel?.toModel(),
        dialogEffect = dialogEffectModel?.toModel(),
        changeTextureEffect = changeTextureEffectModel?.toModel()
)

private fun ChangeTextureDefinitionModel.toModel() = ChangeTextureModel(objectId = objectId, texturePath = texturePath)
private fun MovementDefinitionModel.toModel() = MovementModel(objectId = objectId, targetX = targetX, targetY = targetY)

private fun DialogSectionDefinitionModel.toModel() = DialogSectionModel(text = text)
private fun DialogDefinitionModel.toModel() = DialogModel(id = id, sections = sectionModels.map { it.toModel() })