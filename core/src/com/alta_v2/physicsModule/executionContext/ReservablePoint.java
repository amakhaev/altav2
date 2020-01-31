package com.alta_v2.physicsModule.executionContext;

import com.badlogic.gdx.math.Vector2;
import lombok.extern.log4j.Log4j2;

/**
 * Describes value that is distributable for read/write access.
 */
@Log4j2
public final class ReservablePoint extends SimpleReservable<Vector2> {

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
     * @param hashCode  - the hash code of occupant who is trying to make change.
     */
    public void setValue(float x, float y, int hashCode) {
        if (this.occupantHash == null) {
            log.warn("The point must be reserved before write value.");
            return;
        }

        if (this.occupantHash != hashCode) {
            log.error("Attempt to write value by {} but occupant is {}", hashCode, this.occupantHash);
            return;
        }

        this.value.x = x;
        this.value.y = y;
    }

    @Override
    public void setValue(Vector2 coordinates, int hashCode) {
        this.setValue(coordinates.x, coordinates.y, hashCode);
    }
}
