package com.alta_v2.rendering.tiledMapScreen.state

import com.alta_v2.rendering.tiledMapScreen.actors.PersonView
import com.badlogic.gdx.math.Vector2
import lombok.Getter
import lombok.RequiredArgsConstructor
import lombok.Setter

class NpcState(val id: Int) {

    val coordinates = Vector2()
    var view = PersonView.FULL_FACE

    fun updateCoordinates(x: Float, y: Float) {
        coordinates.x = x
        coordinates.y = y
    }
}