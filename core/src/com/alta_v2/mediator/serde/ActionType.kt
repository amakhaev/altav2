package com.alta_v2.mediator.serde

import com.alta_v2.physics.task.MovementDirection

enum class ActionType {

    MOVE_UP, MOVE_RIGHT, MOVE_DOWN, MOVE_LEFT, NEXT, BACK, UNKNOWN;

    companion object {
        fun getMovementDirection(type: ActionType): MovementDirection {
            return when (type) {
                MOVE_UP -> MovementDirection.HIGHER
                MOVE_DOWN -> MovementDirection.LOWER
                MOVE_LEFT -> MovementDirection.LEFT
                MOVE_RIGHT -> MovementDirection.RIGHT
                else -> MovementDirection.UNKNOWN
            }
        }
    }

}