package com.alta_v2.aop.dynamicAssetLoader;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import lombok.extern.log4j.Log4j2;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;

@Log4j2
public class DynamicAssetHandler implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) {
        Method method = invocation.getMethod();

        log.debug("Initialization asset manager for class {}...", method.getDeclaringClass().getSimpleName());

        Instant start = Instant.now();

        AssetManager assetManager = new AssetManager();
        assetManager.setLoader(TiledMap.class, new TmxMapLoader());

        this.loadTextures(assetManager, method.getAnnotation(DynamicAssetLoader.class).textures());
        this.loadTiledMap(assetManager, method.getAnnotation(DynamicAssetLoader.class).tiledMap());

        assetManager.finishLoading();

        log.info(
                "Asset manager initialized for class {}. Total time: {} ms.",
                method.getDeclaringClass().getSimpleName(),
                Duration.between(start, Instant.now()).toMillis()
        );
        return assetManager;
    }

    private void loadTextures(AssetManager assetManager, String[] textures) {
        this.load(assetManager, textures, Texture.class);
    }

    private void loadTiledMap(AssetManager assetManager, String[] textures) {
        this.load(assetManager, textures, TiledMap.class);
    }

    private <T> void load(AssetManager assetManager, String[] textures, Class<T> type) {
        if (textures == null || textures.length == 0) {
            return;
        }

        for (String fileName: textures) {
            assetManager.load(fileName, type);
        }
    }
}
