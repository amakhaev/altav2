package com.alta_v2.physics.executionContext.reserveData

import com.alta_v2.physics.executionContext.Reservable
import com.alta_v2.physics.executionContext.Tenant
import com.badlogic.gdx.math.Vector2
import mu.KotlinLogging

/**
 * Describes value that is distributable for read/write access.
 */
class ReservablePoint : Reservable<Vector2>() {

    private val log = KotlinLogging.logger {  }

    /**
     * Gets the X coordinate of point.
     */
    val x: Float
        get() = value!!.x

    /**
     * Gets the Y coordinate of point.
     */
    val y: Float
        get() = value!!.y

    init {
        value = Vector2()
    }

    /**
     * Sets the value of point.
     *
     * @param x         - the X value of point.
     * @param y         - the Y value of point.
     * @param tenant    - the hash code of occupant who is trying to make change.
     */
    fun setValue(x: Float, y: Float, tenant: Tenant): ReservablePoint {
        if (this.tenant == null) {
            log.warn("The point must be reserved before write value.")
            return this
        }
        if (this.tenant!!.id != tenant.id) {
            log.error(
                    "Attempt to write value by '{}' but tenant is '{}'",
                    tenant.id,
                    this.tenant!!.id
            )
            return this
        }
        value!!.x = x
        value!!.y = y
        return this
    }

    override fun reserve(tenant: Tenant): ReservablePoint {
        super.reserve(tenant)
        return this
    }

    override fun setValue(coordinates: Vector2, tenant: Tenant): ReservablePoint {
        this.setValue(coordinates.x, coordinates.y, tenant)
        return this
    }
}