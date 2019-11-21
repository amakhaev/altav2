package com.alta_v2.physicsModule;

import lombok.experimental.UtilityClass;

/**
 * Provides utility for make raw physic calculations.
 */
@UtilityClass
class TiledMapPhysicCalculator {

    float focusPointCoordinate(float localCoordinate, int tileSize) {
        return localCoordinate * tileSize + tileSize / 2;
    }

    float playerCoordinate(float tileSize, float screenSize) {
        return screenSize / 2 - tileSize / 2;
    }

}
