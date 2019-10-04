package com.alta_v2.game.configuration;

import com.alta_v2.game.utils.Resources;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.google.inject.Provider;
import lombok.extern.log4j.Log4j2;

import java.time.Duration;
import java.time.Instant;

/**
 * Describes the provider for getting {@link AssetManager} via Guice.
 */
@Log4j2
public class AssetManagerProvider implements Provider<AssetManager> {

    /**
     * {@inheritDoc}
     */
    @Override
    public AssetManager get() {
        log.info("Initialization asset manager...");

        Instant start = Instant.now();

        AssetManager assetManager = new AssetManager();
        assetManager.setLoader(TiledMap.class, new TmxMapLoader());

        assetManager.load(Resources.ACTOR_PERSON_12, Texture.class);
        assetManager.load(Resources.MAP_TEST, TiledMap.class);
        assetManager.load(Resources.TEXTURE_BLACK_SQUARE, Texture.class);

        assetManager.finishLoading();

        log.info(
                "Asset manager initialized. Total time: {} ms.",
                Duration.between(start, Instant.now()).toMillis()
        );
        return assetManager;
    }
}
