package com.alta_v2.game.gamelogic.data.interaction

data class EffectModel(val effectId: Int,
                       val changeTextureEffect: ChangeTextureModel? = null,
                       val movementEffect: MovementModel? = null,
                       val dialogEffect: DialogModel? = null)

data class ChangeTextureModel(val objectId: Int, val texturePath: String)

data class MovementModel(val objectId: Int, val targetX: Int, val targetY: Int)

data class DialogModel(val id: Int, val sections: List<DialogSectionModel>)
data class DialogSectionModel(val text: String)