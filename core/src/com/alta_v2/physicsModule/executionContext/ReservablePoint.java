package com.alta_v2.physicsModule.executionContext;

import com.badlogic.gdx.math.Vector2;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

/**
 * Describes value that is distributable for read/write access.
 */
@Log4j2
public final class ReservablePoint {

    private Vector2 value;
    private Integer occupantHash;

    @Getter
    private boolean isReserved = false;

    /**
     * Initialize new instance of {@link ReservablePoint}.
     */
    public ReservablePoint() {
        this.value = new Vector2();
    }

    /**
     * Reserves the value for permanent write access.
     *
     * @param hashCode - the hash code of occupant.
     */
    public synchronized ReservablePoint reserve(int hashCode) {
        if (this.occupantHash != null) {
            log.error("Value has reserved by another occupant with hash {}", this.occupantHash);
            return this;
        }

        this.occupantHash = hashCode;
        this.isReserved = true;
        return this;
    }

    /**
     * Releases the current value, then value can be used for write by another occupants.
     *
     * @param hashCode - the hash code of current occupant.
     */
    public synchronized ReservablePoint release(int hashCode) {
        if (this.occupantHash == null) {
            log.warn("Value already free");
            return this;
        }

        if (this.occupantHash != hashCode) {
            log.error(
                    "Reserved value could be released by occupant only. Current occupant hash code: {}, trying to release by : {}",
                    this.occupantHash,
                    hashCode
            );
            return this;
        }

        this.occupantHash = null;
        this.isReserved = false;
        return this;
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
    public ReservablePoint setValue(float x, float y, int hashCode) {
        if (this.occupantHash == null) {
            log.warn("The point must be reserved before write value.");
            return this;
        }

        if (this.occupantHash != hashCode) {
            log.error("Attempt to write value by {} but occupant is {}", hashCode, this.occupantHash);
            return this;
        }

        this.value.x = x;
        this.value.y = y;
        return this;
    }

    /**
     * Sets the value of point.
     *
     * @param coordinates   - the new coordinates of point.
     * @param hashCode      - the hash code of occupant who is trying to make change.
     */
    public ReservablePoint setValue(Vector2 coordinates, int hashCode) {
        return this.setValue(coordinates.x, coordinates.y, hashCode);
    }
}
