package com.alta_v2.rendering.tiledMapScreen.state

import com.alta_v2.rendering.tiledMapScreen.actors.PersonView
import com.badlogic.gdx.math.Vector2

/**
 * Provides the state of [com.alta_v2.rendering.tiledMapScreen.actors.player.PlayerActor]
 */
class PlayerState {

    val coordinates = Vector2()

    var view = PersonView.FULL_FACE
    var isAnimationEnabled = false
    var animationChangeTime: Long = 0

    fun updateCoordinates(x: Float, y: Float) {
        coordinates.x = x
        coordinates.y = y
    }
}