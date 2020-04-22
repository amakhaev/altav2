package com.alta_v2.game.gamelogic.data.npc

data class NpcModel(val id: Int,
                    val localX: Float,
                    val localY: Float,
                    val repeatMovementInterval: Int,
                    val interactionGroupId: Int?) {

    var lastMovementMills: Long = 0
    var isMovementRunning: Boolean = false
}