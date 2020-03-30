package com.alta_v2.utils;

import com.alta_v2.aop.executionTime.PrintExecutionTime;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.google.common.base.Strings;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Log4j2
@UtilityClass
public class AssetLoader {

    /**
     * Loads textures into assets manager.
     *
     * @param mapTexture    - the path to texture of map.
     * @param otherTextures - the list of other textures to be loaded.
     * @return created {@link AssetManager} instance.
     */
    @PrintExecutionTime
    public AssetManager load(String mapTexture, List<String> otherTextures) {
        AssetManager assetManager = new AssetManager();

        if (otherTextures != null) {
            otherTextures.forEach(texture -> assetManager.load(texture, Texture.class));
        }

        if (!Strings.isNullOrEmpty(mapTexture)) {
            assetManager.setLoader(TiledMap.class, new TmxMapLoader());
            assetManager.load(mapTexture, TiledMap.class);
        }
        assetManager.finishLoading();
        return assetManager;
    }

    public AssetManager load(String texture) {
        Instant start = Instant.now();

        AssetManager assetManager = new AssetManager();

        if (!Strings.isNullOrEmpty(texture)) {
            assetManager.load(texture, Texture.class);
        }
        assetManager.finishLoading();

        log.debug(
                "Assets were loaded. Total time: {}",
                Duration.between(start, Instant.now()).toMillis()
        );

        return assetManager;
    }
}
