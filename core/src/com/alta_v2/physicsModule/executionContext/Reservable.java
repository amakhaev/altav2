package com.alta_v2.physicsModule.executionContext;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

/**
 * Provides base reservable object.
 */
@Log4j2
public abstract class Reservable<T> {

    protected T value;
    protected Tenant tenant;

    @Getter
    protected boolean isReserved = false;

    /**
     * Reserves the value for permanent write access.
     *
     * @param tenant - the tenant of reservable variable.
     */
    public synchronized Reservable<T> reserve(Tenant tenant) {
        if (tenant == null) {
            log.error("Given tenant is null");
            return this;
        }

        if (this.tenant != null) {
            log.warn("It already reserved by tenant with ID '{}'", this.tenant.getId());
            return this;
        }

        this.tenant = tenant;
        this.isReserved = true;
        return this;
    }

    /**
     * Releases the current value, then value can be used for write by another occupants.
     *
     * @param tenant - the tenant of reservable variable.
     */
    public synchronized Reservable<T> release(Tenant tenant) {
        if (tenant == null) {
            log.error("Given tenant is null");
            return this;
        }

        if (this.tenant == null) {
            log.warn("Variable is not reserved by any tenant");
            return this;
        }

        if (!this.tenant.getId().equals(tenant.getId())) {
            log.debug("Reserved value can be released by tenant '{}' only. Attempt to release by : '{}'",
                    this.tenant.getId(), tenant.getId()
            );
            return this;
        }

        this.tenant = null;
        this.isReserved = false;
        return this;
    }

    /**
     * Sets the value of point.
     *
     * @param value - the new value.
     * @param tenant - the tenant of reservable variable.
     */
    public Reservable<T> setValue(T value, Tenant tenant) {
        if (tenant == null) {
            log.error("Given tenant is null");
            return this;
        }

        if (this.tenant == null) {
            log.warn("Variable is not reserved by any tenant");
            return this;
        }

        if (!this.tenant.getId().equals(tenant.getId())) {
            log.debug("Reserved value can be changed by tenant '{}' only. Attempt to change by : '{}'",
                    this.tenant.getId(), tenant.getId()
            );
            return this;
        }

        this.value = value;
        return this;
    }

    /**
     * Indicates when object is occupied.
     */
    protected final boolean isOccupied() {
        return this.tenant != null;
    }

    /**
     * Indicates when object is occupied by given hashCode.
     */
    protected final boolean isOccupiedBy(Tenant tenant) {
        return this.tenant.getId().equals(tenant.getId());
    }

    protected final boolean isAvailableToChange(Tenant tenant) {
        if (!this.isOccupied()) {
            log.trace("Value already free");
            return false;
        }

        if (!this.isOccupiedBy(tenant)) {
            log.error(
                    "Reserved value can be changed by tenant '{}' only. Attempt to release by : '{}'",
                    this.tenant.getId(),
                    tenant.getId()
            );
            return false;
        }

        return true;
    }
}
