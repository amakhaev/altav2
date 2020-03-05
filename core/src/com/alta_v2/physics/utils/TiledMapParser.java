package com.alta_v2.physics.utils;

import com.alta_v2.physics.executionContext.AltitudeMap;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import lombok.experimental.UtilityClass;

/**
 * Provides the parser for tiled map.
 */
@UtilityClass
public class TiledMapParser {

    public AltitudeMap parse(String path) {
        TiledMap tiledMap = loadMap(path);

        int countX = tiledMap.getProperties().get("width", Integer.class);
        int countY = tiledMap.getProperties().get("height", Integer.class);

        try {
            return AltitudeMap.builder()
                    .horizontalTilesCount(countX)
                    .verticalTilesCount(countY)
                    .tileWidth(tiledMap.getProperties().get("tilewidth", Integer.class))
                    .tileHeight(tiledMap.getProperties().get("tileheight", Integer.class))
                    .altitudes(getAltitudes(tiledMap, countX, countY))
                    .build();
        } finally {
            tiledMap.dispose();
        }
    }

    private TiledMap loadMap(String path) {
        AssetManager assetManager = new AssetManager();
        assetManager.setLoader(TiledMap.class, new TmxMapLoader());
        assetManager.load(path, TiledMap.class);
        assetManager.finishLoading();

        TiledMap tiledMap = assetManager.get(path, TiledMap.class);
        assetManager.dispose();
        return tiledMap;
    }

    private AltitudeMap.PointAvailability[][] getAltitudes(TiledMap tiledMap, int countX, int countY) {
        TiledMapTileLayer barrierLayer = (TiledMapTileLayer) tiledMap.getLayers().get("over_barrier");
        AltitudeMap.PointAvailability[][] altitudes = new AltitudeMap.PointAvailability[countX][countY];

        for (int i = 0; i < countX; i++) {
            for (int j = 0; j < countY; j++) {
                if (barrierLayer.getCell(i, j) == null) {
                    altitudes[i][j] = AltitudeMap.PointAvailability.FREE;
                } else {
                    altitudes[i][j] = AltitudeMap.PointAvailability.BARRIER;
                }
            }
        }

        return altitudes;
    }
}