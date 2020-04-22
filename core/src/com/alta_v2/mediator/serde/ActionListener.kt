package com.alta_v2.mediator.serde

import com.alta_v2.common.Destroyable

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

    companion object {
        /**
         * Resolves the input to game action.
         *
         * @param keyCode - the key code.
         * @return the [ActionType] instance.
         */
        fun resolveAction(keyCode: Int): ActionType {
            when (keyCode) {
                34 -> return ActionType.NEXT  // F
                35 -> return ActionType.BACK  // G
                22 -> return ActionType.MOVE_RIGHT
                20 -> return ActionType.MOVE_DOWN
                21 -> return ActionType.MOVE_LEFT
                19 -> return ActionType.MOVE_UP
            }
            return ActionType.UNKNOWN
        }
    }
}