package com.alta_v2.physics.executionContext.reserveData

import com.alta_v2.physics.executionContext.Reservable
import com.alta_v2.physics.executionContext.Tenant

/**
 * Provides the reservable object for boolean value.
 */
class ReservableBoolean : Reservable<Boolean>() {

    private var changeTime: Long = 0

    init {
        this.value = false
    }

    override fun setValue(value: Boolean, tenant: Tenant): ReservableBoolean {
        super.setValue(value, tenant)
        changeTime = System.currentTimeMillis()
        return this
    }

    fun getChangeTime() = changeTime

    fun getValue() = value ?: false
}