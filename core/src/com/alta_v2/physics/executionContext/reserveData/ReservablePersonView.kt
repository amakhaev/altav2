package com.alta_v2.physics.executionContext.reserveData

import com.alta_v2.physics.executionContext.Reservable
import com.alta_v2.rendering.tiledMapScreen.actors.PersonView

class ReservablePersonView : Reservable<PersonView>() {

    init {
        value = PersonView.FULL_FACE
    }

    fun getValue() = value ?: PersonView.FULL_FACE
}