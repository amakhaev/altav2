package com.alta_v2.physics.utils

import com.alta_v2.physics.executionContext.TiledMapEngineContext

/**
 * Provides utility for make raw physic calculations.
 */
class TiledMapPhysicCalculator {

    companion object {
        fun centerTileCoordinate(localCoordinate: Float, tileSize: Int): Float {
            return localToGlobal(localCoordinate, tileSize) + tileSize / 2
        }

        fun centerTileCoordinateX(context: TiledMapEngineContext): Float {
            return centerTileCoordinate(context.focusPointLocal.x, context.altitudeMap.tileWidth)
        }

        fun centerTileCoordinateY(context: TiledMapEngineContext): Float {
            return centerTileCoordinate(context.focusPointLocal.y, context.altitudeMap.tileHeight)
        }

        fun localToGlobal(localCoordinate: Float, tileSize: Int): Float {
            return localCoordinate * tileSize
        }

        fun percentByValue(value: Float, percent: Float): Float {
            return value / 100 * percent
        }

        fun playerCoordinate(tileSize: Float, screenSize: Float): Float {
            return screenSize / 2 - tileSize / 2
        }
    }
}