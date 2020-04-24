package com.alta_v2.model

data class UiEffectDefinition(val id: Int,
                              val changeTextureEffect: ChangeTextureDefinition? = null,
                              val movementEffect: MovementDefinition? = null,
                              val dialogEffect: DialogDefinition? = null)

data class ChangeTextureDefinition(val objectId: Int, val texturePath: String)

data class MovementDefinition(val objectId: Int, val targetX: Int, val targetY: Int)

data class DialogDefinition(val id: Int, val sections: List<DialogSectionDefinition>)
data class DialogSectionDefinition(val text: String)