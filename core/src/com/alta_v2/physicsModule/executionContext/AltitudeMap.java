package com.alta_v2.physicsModule.executionContext;

import lombok.Builder;
import lombok.Getter;

/**
 * Describes the altitude map.
 */
@Builder
public final class AltitudeMap {

    private final int[][] altitudes;

    @Getter
    private final int tileWidth;

    @Getter
    private final int tileHeight;
    private final int horizontalTilesCount;
    private final int verticalTilesCount;

}
