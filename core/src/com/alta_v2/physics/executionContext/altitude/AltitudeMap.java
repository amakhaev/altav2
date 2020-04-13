package com.alta_v2.physics.executionContext.altitude;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

/**
 * Describes the altitude map.
 */
@Log4j2
@Builder
public final class AltitudeMap {

    private final AltitudeMapPoint[][] altitudes;

    @Getter
    private final int tileWidth;

    @Getter
    private final int tileHeight;
    private final int horizontalTilesCount;
    private final int verticalTilesCount;

    /**
     * Gets the status of point on map.
     */
    public PointAvailability getPointStatus(int x, int y) {
        if (isOutOfBounds(x, y)) {
            return null;
        }

        return altitudes[x][y].getPointAvailability();
    }

    /**
     * Gets the ID of object o given position.
     */
    public Integer getObjectId(int x, int y) {
        if (isOutOfBounds(x, y)) {
            return null;
        }

        return altitudes[x][y].getObjectId();
    }

    /**
     * Marks point as free for movement.
     *
     * @param x - the X coordinate of point.
     * @param y - the Y coordinate of point.
     */
    public void markAsFree(int x, int y) {
        if (isOutOfBounds(x, y)) {
            log.error("Index of bound for altitudes [{}: {}]", x, y);
            return;
        }

        altitudes[x][y].setPointAvailability(PointAvailability.FREE);
        altitudes[x][y].setObjectId(null);
    }

    /**
     * Marks point as barrier for movement.
     *
     * @param x - the X coordinate of point.
     * @param y - the Y coordinate of point.
     */
    public void markAsBarrier(int x, int y) {
        if (isOutOfBounds(x, y)) {
            log.error("Index of bound for altitudes [{}: {}]", x, y);
            return;
        }

        altitudes[x][y].setPointAvailability(PointAvailability.BARRIER);
    }

    /**
     * Marks point as busy for movement by objectId.
     *
     * @param x - the X coordinate of point.
     * @param y - the Y coordinate of point.
     * @param objectId - the ID of object.
     */
    public void markAsObject(int x, int y, int objectId) {
        if (isOutOfBounds(x, y)) {
            log.error("Index of bound for altitudes [{}: {}]", x, y);
            return;
        }

        altitudes[x][y].setPointAvailability(PointAvailability.OBJECT);
        altitudes[x][y].setObjectId(objectId);
    }

    private boolean isOutOfBounds(int x, int y) {
        return x < 0 || x >= this.altitudes.length || y < 0 || y >= this.altitudes[x].length;
    }
}
