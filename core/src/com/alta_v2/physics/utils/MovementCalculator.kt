package com.alta_v2.physics.utils

import com.alta_v2.physics.executionContext.altitude.AltitudeMap
import com.alta_v2.physics.executionContext.altitude.PointAvailability
import com.alta_v2.physics.task.MovementDirection
import com.badlogic.gdx.math.Vector2

class MovementCalculator {

    companion object {
        /**
         * Gets the target point of movement as local coordinates.
         *
         * @param direction - the movement direction.
         * @param startX    - start X coordinate.
         * @param startY    - start Y coordinate.
         * @return the target coordinates.
         */
        fun getTargetPointLocal(direction: MovementDirection, startX: Float, startY: Float): Vector2 {
            var newStartX = startX
            var newStartY = startY
            when (direction) {
                MovementDirection.LEFT -> newStartX--
                MovementDirection.RIGHT -> newStartX++
                MovementDirection.HIGHER -> newStartY++
                MovementDirection.LOWER -> newStartY--
            }
            return Vector2(newStartX, newStartY)
        }

        /**
         * Indicates when movement ot target point is available.
         *
         * @param localX        - the X target local coordinate.
         * @param localY        - the Y target local coordinate.
         * @param altitudeMap   - the [AltitudeMap] instance.
         * @return true if movement is available, false otherwise.
         */
        fun canMoveTo(localX: Float, localY: Float, altitudeMap: AltitudeMap): Boolean {
            return altitudeMap.getPointStatus(localX.toInt(), localY.toInt()) === PointAvailability.FREE
        }
    }
}