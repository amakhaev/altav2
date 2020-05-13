package com.alta_v2.model

data class UiEffectDefinitionModel(val id: Int,
                                   val changeTextureEffectModel: ChangeTextureDefinitionModel? = null,
                                   val movementEffectModel: MovementDefinitionModel? = null,
                                   val dialogEffectModel: DialogDefinitionModel? = null)

data class ChangeTextureDefinitionModel(val objectId: Int, val texturePath: String)

data class MovementDefinitionModel(val objectId: Int, val targetX: Int, val targetY: Int)

data class DialogDefinitionModel(val id: Int, val sectionModels: List<DialogSectionDefinitionModel>)
data class DialogSectionDefinitionModel(val text: String)