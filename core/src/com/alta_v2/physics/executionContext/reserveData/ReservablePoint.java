package com.alta_v2.physics.executionContext.reserveData;

import com.alta_v2.physics.executionContext.Reservable;
import com.alta_v2.physics.executionContext.Tenant;
import com.badlogic.gdx.math.Vector2;
import lombok.extern.log4j.Log4j2;

/**
 * Describes value that is distributable for read/write access.
 */
@Log4j2
public final class ReservablePoint extends Reservable<Vector2> {

    /**
     * Initialize new instance of {@link ReservablePoint}.
     */
    public ReservablePoint() {
        this.value = new Vector2();
    }

    /**
     * Gets the X coordinate of point.
     */
    public float getX() {
        return this.value.x;
    }

    /**
     * Gets the Y coordinate of point.
     */
    public float getY() {
        return this.value.y;
    }

    /**
     * Sets the value of point.
     *
     * @param x         - the X value of point.
     * @param y         - the Y value of point.
     * @param tenant    - the hash code of occupant who is trying to make change.
     */
    public ReservablePoint setValue(float x, float y, Tenant tenant) {
        if (this.tenant == null) {
            log.warn("The point must be reserved before write value.");
            return this;
        }

        if (!this.tenant.getId().equals(tenant.getId())) {
            log.error(
                    "Attempt to write value by '{}' but tenant is '{}'",
                    tenant.getId(),
                    this.tenant.getId()
            );
            return this;
        }

        this.value.x = x;
        this.value.y = y;
        return this;
    }

    @Override
    public ReservablePoint reserve(Tenant tenant) {
        super.reserve(tenant);
        return this;
    }

    @Override
    public ReservablePoint setValue(Vector2 coordinates, Tenant tenant) {
        this.setValue(coordinates.x, coordinates.y, tenant);
        return this;
    }
}
