package com.alta_v2.physicsModule.utils;

import com.alta_v2.physicsModule.executionContext.AltitudeMap;
import com.alta_v2.physicsModule.task.MovementDirection;
import com.badlogic.gdx.math.Vector2;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MovementCalculator {

    /**
     * Gets the target point of movement as local coordinates.
     *
     * @param direction - the movement direction.
     * @param startX    - start X coordinate.
     * @param startY    - start Y coordinate.
     * @return the target coordinates.
     */
    public Vector2 getTargetPointLocal(MovementDirection direction, float startX, float startY) {
        switch (direction) {
            case LEFT:
                startX--;
                break;
            case RIGHT:
                startX++;
                break;
            case HIGHER:
                startY++;
                break;
            case LOWER:
                startY--;
                break;
        }

        return new Vector2(startX, startY);
    }

    /**
     * Indicates when movement ot target point is available.
     *
     * @param localX        - the X target local coordinate.
     * @param localY        - the Y target local coordinate.
     * @param altitudeMap   - the {@link AltitudeMap} instance.
     * @return true if movement is available, false otherwise.
     */
    public boolean canMoveTo(float localX, float localY, AltitudeMap altitudeMap) {
        return altitudeMap.getPointStatus((int)localX, (int)localY) == AltitudeMap.PointAvailability.FREE;
    }
}
