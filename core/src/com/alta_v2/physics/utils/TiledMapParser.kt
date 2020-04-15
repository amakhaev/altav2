package com.alta_v2.physics.utils

import com.alta_v2.physics.executionContext.altitude.AltitudeMap
import com.alta_v2.physics.executionContext.altitude.AltitudeMapPoint
import com.alta_v2.physics.executionContext.altitude.PointAvailability
import com.alta_v2.utils.MAP_HEIGHT
import com.alta_v2.utils.MAP_WIDTH
import com.alta_v2.utils.TILE_HEIGHT
import com.alta_v2.utils.TILE_WIDTH
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer
import com.badlogic.gdx.maps.tiled.TmxMapLoader

/**
 * Provides the parser for tiled map.
 */
class TiledMapParser {

    companion object {
        fun parse(path: String): AltitudeMap {
            val tiledMap = loadMap(path)
            val countX = tiledMap.properties.get(MAP_WIDTH, Int::class.java)
            val countY = tiledMap.properties.get(MAP_HEIGHT, Int::class.java)
            return try {
                AltitudeMap(
                        tileWidth = tiledMap.properties.get(TILE_WIDTH, Int::class.java),
                        tileHeight = tiledMap.properties.get(TILE_HEIGHT, Int::class.java),
                        altitudes = getAltitudes(tiledMap, countX, countY)
                )
            } finally {
                tiledMap.dispose()
            }
        }

        private fun loadMap(path: String): TiledMap {
            val assetManager = AssetManager()
            assetManager.setLoader(TiledMap::class.java, TmxMapLoader())
            assetManager.load(path, TiledMap::class.java)
            assetManager.finishLoading()
            val tiledMap = assetManager.get(path, TiledMap::class.java)
            assetManager.dispose()
            return tiledMap
        }

        private fun getAltitudes(tiledMap: TiledMap, countX: Int, countY: Int): Array<Array<AltitudeMapPoint>> {
            val barrierLayer = tiledMap.layers["over_barrier"] as TiledMapTileLayer
            // val altitudes = Array(countX) { arrayOfNulls<AltitudeMapPoint>(countY) }

            return Array(countX) {i ->
                Array(countY) { j ->
                    if (barrierLayer.getCell(i, j) == null) {
                        AltitudeMapPoint(PointAvailability.FREE)
                    } else {
                        AltitudeMapPoint(PointAvailability.BARRIER)
                    }
                }
            }
        }
    }
}