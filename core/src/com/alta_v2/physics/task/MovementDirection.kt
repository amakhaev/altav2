package com.alta_v2.physics.task

import com.alta_v2.rendering.tiledMapScreen.actors.PersonView
import java.util.*

/**
 * Provides available directions for movement.
 */
enum class MovementDirection {
    HIGHER, LOWER, RIGHT, LEFT, UNKNOWN;

    companion object {
        private val VALUES = listOf<MovementDirection>(HIGHER, LOWER, RIGHT, LEFT)
        private val SIZE = VALUES.size
        private val RANDOM = Random()
        fun randomDirection(): MovementDirection {
            return VALUES[RANDOM.nextInt(SIZE)]
        }

        fun getPersonView(direction: MovementDirection): PersonView {
            return when (direction) {
                HIGHER -> PersonView.BACK_VIEW
                RIGHT -> PersonView.SIDE_VIEW_RIGHT
                LEFT -> PersonView.SIDE_VIEW_LEFT
                LOWER,
                UNKNOWN -> PersonView.FULL_FACE
            }
        }
    }
}