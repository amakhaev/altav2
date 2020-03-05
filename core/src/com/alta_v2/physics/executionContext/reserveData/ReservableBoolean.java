package com.alta_v2.physics.executionContext.reserveData;

import com.alta_v2.physics.executionContext.Reservable;
import com.alta_v2.physics.executionContext.Tenant;
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

    public ReservableBoolean setValue(boolean value, Tenant tenant) {
        super.setValue(value, tenant);
        this.changeTime = System.currentTimeMillis();
        return this;
    }
}
