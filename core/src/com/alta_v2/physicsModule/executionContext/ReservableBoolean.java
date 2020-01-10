package com.alta_v2.physicsModule.executionContext;

import lombok.Getter;

/**
 * Provides the reservable object for boolean value.
 */
public class ReservableBoolean extends Reservable<Boolean> {

    @Getter
    private long changeTime;

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

    /**
     * Sets the boolean value.
     *
     * @param value         - the new value.
     * @param hashCode      - the hash code of occupant who is trying to make change.
     */
    public void setValue(boolean value, int hashCode) {
        super.setValue(value, hashCode);
        this.changeTime = System.currentTimeMillis();
    }
}
