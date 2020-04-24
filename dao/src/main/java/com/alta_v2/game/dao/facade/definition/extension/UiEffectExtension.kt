package com.alta_v2.game.dao.facade.definition.extension

import com.alta_v2.game.dao.data.effect.DialogSectionEntity
import com.alta_v2.game.dao.data.effect.EffectAggregationEntity
import com.alta_v2.model.*
import mu.KotlinLogging

private val log = KotlinLogging.logger {  }

internal fun EffectAggregationEntity.toUiEffectDefinition() = UiEffectDefinition(
        id = effectId,
        changeTextureEffect = createTextureDefinition(this),
        dialogEffect = createDialogDefinition(this),
        movementEffect = createMovementDefinition(this)
)

private fun createTextureDefinition(aggregate: EffectAggregationEntity) =
        if (aggregate.hasRequiredDataForChangeTexture()) {
            ChangeTextureDefinition(objectId = aggregate.targetId!!, texturePath = aggregate.texturePath!!)
        } else {
            log.trace("Required data to create change texture effect not found")
            null
        }

private fun createMovementDefinition(aggregate: EffectAggregationEntity) =
        if (aggregate.hasRequiredDataForMovement()) {
            MovementDefinition(
                    objectId = aggregate.targetId!!, targetX = aggregate.movementX!!, targetY = aggregate.movementY!!
            )
        } else {
            log.trace("Required data to create movement effect not found")
            null
        }

private fun createDialogDefinition(aggregate: EffectAggregationEntity) =
        if (aggregate.hasRequiredDataForDialog()) {
            DialogDefinition(id = aggregate.dialogId!!, sections = aggregate.dialogSections.map { it.toDefinition() })
        } else {
            log.trace("Required data to create dialog effect not found")
            null
        }

private fun DialogSectionEntity.toDefinition() = DialogSectionDefinition(text = text)

private fun EffectAggregationEntity.hasRequiredDataForChangeTexture() =
        targetId != null && changeTextureFolder != null && changeTextureName != null

private fun EffectAggregationEntity.hasRequiredDataForMovement() =
        targetId != null && movementX != null && movementY != null

private fun EffectAggregationEntity.hasRequiredDataForDialog() = dialogId != null && dialogSections.isNotEmpty()