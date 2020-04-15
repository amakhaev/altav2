package com.alta_v2.physics.executionContext.reserveData

import com.alta_v2.physics.executionContext.Reservable

/**
 * Describes value that is distributable for read/write access.
 */
class ReservableActor(val id: Int) : Reservable<Boolean>() {

    val globalPoint = ReservablePoint()
    val localPoint = ReservablePoint()
    val view = ReservablePersonView()
    val isMoving: ReservableBoolean = ReservableBoolean()

    init {
        value = false
    }

    fun getValue() = value
}