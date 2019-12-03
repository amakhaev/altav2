package com.alta_v2.physicsModule.executionContext;

/**
 * Provides the reservable object for boolean value.
 */
public class ReservableBoolean extends Reservable<Boolean> {

    /**
     * Initialize new instance of {@link ReservableBoolean}.
     */
    public ReservableBoolean() {
        this.value = false;
    }

    /**
     * Gets the reserved value.
     */
    public boolean getValue() {
        return this.value;
    }
}
