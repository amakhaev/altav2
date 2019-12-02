package com.alta_v2.physicsModule.utils;

import com.alta_v2.physicsModule.executionContext.TiledMapEngineContext;
import lombok.experimental.UtilityClass;

/**
 * Provides utility for make raw physic calculations.
 */
@UtilityClass
public class TiledMapPhysicCalculator {

    public float centerTileCoordinate(float localCoordinate, int tileSize) {
        return localToGlobal(localCoordinate, tileSize) + tileSize / 2;
    }

    public float centerTileCoordinateX(TiledMapEngineContext context) {
        return centerTileCoordinate(context.getFocusPointLocal().getX(), context.getAltitudeMap().getTileWidth());
    }

    public float centerTileCoordinateY(TiledMapEngineContext context) {
        return centerTileCoordinate(context.getFocusPointLocal().getY(), context.getAltitudeMap().getTileHeight());
    }

    public float localToGlobal(float localCoordinate, int tileSize) {
        return localCoordinate * tileSize;
    }

    public float percentByValue(float value, float percent) {
        return value / 100 * percent;
    }

    public float playerCoordinate(float tileSize, float screenSize) {
        return screenSize / 2 - tileSize / 2;
    }
}
