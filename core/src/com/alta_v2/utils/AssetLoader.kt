package com.alta_v2.utils

import com.alta_v2.aop.executionTime.PrintExecutionTime
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.google.common.base.Strings
import lombok.experimental.UtilityClass
import lombok.extern.log4j.Log4j2
import mu.KotlinLogging
import java.time.Duration
import java.time.Instant
import java.util.function.Consumer

class AssetLoader {

    companion object {
        private val log = KotlinLogging.logger {  }

        /**
         * Loads textures into assets manager.
         *
         * @param mapTexture    - the path to texture of map.
         * @param otherTextures - the list of other textures to be loaded.
         * @return created [AssetManager] instance.
         */
        @PrintExecutionTime
        fun load(mapTexture: String, otherTextures: List<String>): AssetManager {
            val assetManager = AssetManager()
            otherTextures.forEach(Consumer { texture: String -> assetManager.load(texture, Texture::class.java) })
            if (!Strings.isNullOrEmpty(mapTexture)) {
                assetManager.setLoader(TiledMap::class.java, TmxMapLoader())
                assetManager.load(mapTexture, TiledMap::class.java)
            }
            assetManager.finishLoading()
            return assetManager
        }

        fun load(texture: String): AssetManager {
            val start = Instant.now()
            val assetManager = AssetManager()
            if (!Strings.isNullOrEmpty(texture)) {
                assetManager.load(texture, Texture::class.java)
            }
            assetManager.finishLoading()
            log.debug(
                    "Assets were loaded. Total time: {}",
                    Duration.between(start, Instant.now()).toMillis()
            )
            return assetManager
        }
    }
}