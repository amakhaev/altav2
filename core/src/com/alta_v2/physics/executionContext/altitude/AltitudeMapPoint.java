package com.alta_v2.physics.executionContext.altitude;

import lombok.Data;

/**
 * Provides the point that equals one logical unit of map.
 */
@Data
public class AltitudeMapPoint {

    private Integer objectId;
    private PointAvailability pointAvailability;

    public AltitudeMapPoint(PointAvailability pointAvailability) {
        this.pointAvailability = pointAvailability;
    }

}
