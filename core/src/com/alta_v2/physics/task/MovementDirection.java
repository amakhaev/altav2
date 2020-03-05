package com.alta_v2.physics.task;

import com.alta_v2.rendering.actors.PersonView;

/**
 * Provides available directions for movement.
 */
public enum MovementDirection {
    HIGHER,
    LOWER,
    RIGHT,
    LEFT;

    public static PersonView getPersonView(MovementDirection direction) {
        switch (direction) {
            case HIGHER:
                return PersonView.BACK_VIEW;
            case LOWER:
                return PersonView.FULL_FACE;
            case RIGHT:
                return PersonView.SIDE_VIEW_RIGHT;
            case LEFT:
                return PersonView.SIDE_VIEW_LEFT;
            default:
                return null;
        }
    }
}
