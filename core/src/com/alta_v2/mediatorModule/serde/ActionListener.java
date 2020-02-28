package com.alta_v2.mediatorModule.serde;

import com.alta_v2.physicsModule.task.MovementDirection;

/**
 * Provides the handler of input actions.
 */
public interface ActionListener {

    /**
     * Handles the action when it begins but not finished yet (like key pressed).
     *
     * @param action - the actions to be handled.
     */
    void onActionBegin(ActionType action);

    /**
     * Handles the action when it finishes (like key pressed).
     *
     * @param action - the actions to be handled.
     */
    void onActionFinish(ActionType action);

    /**
     * Destroys the controller.
     */
    void destroy();

    /**
     * Resolves the input to game action.
     *
     * @param keyCode - the key code.
     * @return the {@link ActionType} instance.
     */
    static ActionType resolveAction(int keyCode) {
        switch (keyCode) {
            case 34: // F
                return ActionType.NEXT;
            case 35: // G
                return ActionType.BACK;
            case 22: // right arrow
                return ActionType.MOVE_RIGHT;
            case 20: // down arrow
                return ActionType.MOVE_DOWN;
            case 21: // left arrow
                return ActionType.MOVE_LEFT;
            case 19: // up arrow
                return ActionType.MOVE_UP;
        }

        return null;
    }

    /**
     * Available types of screen action.
     */
    enum ActionType {
        MOVE_UP,
        MOVE_RIGHT,
        MOVE_DOWN,
        MOVE_LEFT,
        NEXT,
        BACK;

        public static MovementDirection getMovementDirection(ActionType type) {
            switch (type) {
                case MOVE_UP:
                    return MovementDirection.HIGHER;
                case MOVE_DOWN:
                    return MovementDirection.LOWER;
                case MOVE_LEFT:
                    return MovementDirection.LEFT;
                case MOVE_RIGHT:
                    return MovementDirection.RIGHT;
                default:
                    return null;
            }
        }
    }
}
