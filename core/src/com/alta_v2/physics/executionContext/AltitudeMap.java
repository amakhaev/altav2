package com.alta_v2.physics.executionContext;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

/**
 * Describes the altitude map.
 */
@Log4j2
@Builder
public final class AltitudeMap {

    private final PointAvailability[][] altitudes;

    @Getter
    private final int tileWidth;

    @Getter
    private final int tileHeight;
    private final int horizontalTilesCount;
    private final int verticalTilesCount;

    public enum PointAvailability {
        FREE,
        BARRIER
    }

    /**
     * Gets the status of point on map.
     */
    public PointAvailability getPointStatus(int x, int y) {
        if (x < 0 || x > this.altitudes.length || y < 0 || y > this.altitudes[x].length) {
            return null;
        }

        return this.altitudes[x][y];
    }

    public void setPointStatus(int x, int y, PointAvailability availability) {
        if (x < 0 || x > this.altitudes.length || y < 0 || y > this.altitudes[x].length) {
            log.error("Index of bound for altitudes [{}: {}]", x, y);
            return;
        }

        this.altitudes[x][y] = availability;
    }
}
