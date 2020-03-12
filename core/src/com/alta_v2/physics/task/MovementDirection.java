package com.alta_v2.physics.task;

import com.alta_v2.rendering.actors.PersonView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Provides available directions for movement.
 */
public enum MovementDirection {
    HIGHER,
    LOWER,
    RIGHT,
    LEFT;

    private static final List<MovementDirection> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static MovementDirection randomDirection()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

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
