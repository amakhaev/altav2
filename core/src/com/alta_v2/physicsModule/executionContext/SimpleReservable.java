package com.alta_v2.physicsModule.executionContext;

import lombok.extern.log4j.Log4j2;

/**
 * Provides simple reservable object that could be rewritten every time.
 */
@Log4j2
public abstract class SimpleReservable<T> extends Reservable<T> {

    /**
     * Sets the value of point.
     *
     * @param value         - the new value.
     * @param hashCode      - the hash code of occupant who is trying to make change.
     */
    public void setValue(T value, int hashCode) {
        if (this.isAvailableToChange(hashCode)) {
            this.value = value;
        }
    }

}
