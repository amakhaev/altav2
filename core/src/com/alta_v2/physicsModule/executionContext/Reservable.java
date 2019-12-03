package com.alta_v2.physicsModule.executionContext;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

/**
 * Provides base reservable object.
 */
@Log4j2
public abstract class Reservable<T> {

    protected T value;
    protected Integer occupantHash;

    @Getter
    protected boolean isReserved = false;

    /**
     * Reserves the value for permanent write access.
     *
     * @param hashCode - the hash code of occupant.
     */
    public synchronized void reserve(int hashCode) {
        if (this.occupantHash != null) {
            log.error("Value has reserved by another occupant with hash {}", this.occupantHash);
            return;
        }

        this.occupantHash = hashCode;
        this.isReserved = true;
    }

    /**
     * Releases the current value, then value can be used for write by another occupants.
     *
     * @param hashCode - the hash code of current occupant.
     */
    public synchronized void release(int hashCode) {
        if (this.occupantHash == null) {
            log.warn("Value already free");
            return;
        }

        if (this.occupantHash != hashCode) {
            log.error(
                    "Reserved value could be released by occupant only. Current occupant hash code: {}, trying to release by : {}",
                    this.occupantHash,
                    hashCode
            );
            return;
        }

        this.occupantHash = null;
        this.isReserved = false;
    }

    /**
     * Sets the value of point.
     *
     * @param value         - the new value.
     * @param hashCode      - the hash code of occupant who is trying to make change.
     */
    public void setValue(T value, int hashCode) {
        if (this.occupantHash == null) {
            log.warn("The value must be reserved before writing.");
            return;
        }

        if (this.occupantHash != hashCode) {
            log.error("Attempt to write value by {} but occupant is {}", hashCode, this.occupantHash);
            return;
        }

        this.value = value;
    }
}
