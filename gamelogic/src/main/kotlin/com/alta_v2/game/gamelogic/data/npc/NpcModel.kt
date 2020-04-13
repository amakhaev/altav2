package com.alta_v2.game.gamelogic.data.npc

data class NpcModel(val id: Int,
                    val localX: Float,
                    val localY: Float,
                    val repeatMovementInterval: Int) {

    var lastMovementMills: Long = 0
        get() = field
        set(value) { field = value }

    var isMovementRunning: Boolean = false
        get() = field
        set(value) { field = value }
}