package com.alta_v2.mediator.serde

import com.alta_v2.common.Destroyable
import com.alta_v2.mediator.serde.ActionListener.ActionType
import com.alta_v2.physics.task.MovementDirection

/**
 * Provides the handler of input actions.
 */
interface ActionListener : Destroyable {
    /**
     * Handles the action when it begins but not finished yet (like key pressed).
     *
     * @param action - the actions to be handled.
     */
    fun onActionBegin(action: ActionType)

    /**
     * Handles the action when it finishes (like key pressed).
     *
     * @param action - the actions to be handled.
     */
    fun onActionFinish(action: ActionType)

    /**
     * Available types of screen action.
     */
    enum class ActionType {
        MOVE_UP, MOVE_RIGHT, MOVE_DOWN, MOVE_LEFT, NEXT, BACK, UNKNOWN;

        companion object {
            fun getMovementDirection(type: ActionType): MovementDirection? {
                return when (type) {
                    MOVE_UP -> MovementDirection.HIGHER
                    MOVE_DOWN -> MovementDirection.LOWER
                    MOVE_LEFT -> MovementDirection.LEFT
                    MOVE_RIGHT -> MovementDirection.RIGHT
                    else -> null
                }
            }
        }
    }

    companion object {
        /**
         * Resolves the input to game action.
         *
         * @param keyCode - the key code.
         * @return the [ActionType] instance.
         */
        fun resolveAction(keyCode: Int): ActionType {
            when (keyCode) {
                34 -> return ActionType.NEXT
                35 -> return ActionType.BACK
                22 -> return ActionType.MOVE_RIGHT
                20 -> return ActionType.MOVE_DOWN
                21 -> return ActionType.MOVE_LEFT
                19 -> return ActionType.MOVE_UP
            }
            return ActionType.UNKNOWN
        }
    }
}