package com.alta_v2.model

class NpcDefinitionModel(id: Int,
                         texturePath: String,
                         x: Float,
                         y: Float,
                         val repeatMovementInterval: Int = 5000) : ActorDefinitionModel(id, texturePath, x, y) {
}