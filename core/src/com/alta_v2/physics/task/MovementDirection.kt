package com.alta_v2.physics.task

import com.alta_v2.rendering.tiledMapScreen.actors.PersonView
import java.util.*

/**
 * Provides available directions for movement.
 */
enum class MovementDirection {
    HIGHER, LOWER, RIGHT, LEFT;

    companion object {
        private val VALUES = Collections.unmodifiableList(listOf(*values()))
        private val SIZE = VALUES.size
        private val RANDOM = Random()
        fun randomDirection(): MovementDirection {
            return VALUES[RANDOM.nextInt(SIZE)]
        }

        fun getPersonView(direction: MovementDirection): PersonView {
            return when (direction) {
                HIGHER -> PersonView.BACK_VIEW
                LOWER -> PersonView.FULL_FACE
                RIGHT -> PersonView.SIDE_VIEW_RIGHT
                LEFT -> PersonView.SIDE_VIEW_LEFT
            }
        }
    }
}