package com.alta_v2.physics.executionContext

import mu.KotlinLogging


/**
 * Provides base reservable object.
 */
abstract class Reservable<T> {

    private val log = KotlinLogging.logger {  }

    @JvmField
    protected var value: T? = null

    @JvmField
    protected var tenant: Tenant? = null

    protected var reserved = false

    fun isReserved() = reserved

    /**
     * Reserves the value for permanent write access.
     *
     * @param tenant - the tenant of reservable variable.
     */
    @Synchronized
    open fun reserve(tenant: Tenant): Reservable<T> {
        if (this.tenant != null) {
            log.debug("It already reserved by tenant with ID '{}'", this.tenant?.id)
            return this
        }

        this.tenant = tenant
        reserved = true
        return this
    }

    /**
     * Releases the current value, then value can be used for write by another occupants.
     *
     * @param tenant - the tenant of reservable variable.
     */
    @Synchronized
    fun release(tenant: Tenant): Reservable<T> {
        if (this.tenant == null) {
            log.warn("Variable is not reserved by any tenant")
            return this
        }
        if (this.tenant!!.id != tenant.id) {
            log.debug("Reserved value can be released by tenant '{}' only. Attempt to release by : '{}'",
                    this.tenant!!.id, tenant.id
            )
            return this
        }
        this.tenant = null
        reserved = false
        return this
    }

    /**
     * Sets the value of point.
     *
     * @param value - the new value.
     * @param tenant - the tenant of reservable variable.
     */
    open fun setValue(value: T, tenant: Tenant): Reservable<T> {
        if (this.tenant == null) {
            log.warn("Variable is not reserved by any tenant")
            return this
        }
        if (this.tenant!!.id != tenant.id) {
            log.debug("Reserved value can be changed by tenant '{}' only. Attempt to change by : '{}'",
                    this.tenant!!.id, tenant.id
            )
            return this
        }
        this.value = value
        return this
    }

    /**
     * Indicates when object is occupied.
     */
    protected fun isOccupied(): Boolean = tenant != null

    /**
     * Indicates when object is occupied by given hashCode.
     */
    protected fun isOccupiedBy(tenant: Tenant): Boolean {
        return this.tenant!!.id == tenant.id
    }

    protected fun isAvailableToChange(tenant: Tenant): Boolean {
        if (!isOccupied()) {
            log.trace("Value already free")
            return false
        }
        if (!isOccupiedBy(tenant)) {
            log.error(
                    "Reserved value can be changed by tenant '{}' only. Attempt to release by : '{}'",
                    this.tenant!!.id,
                    tenant.id
            )
            return false
        }
        return true
    }
}