package com.alta_v2.rendering.tiledMapScreen

/**
 * Provides the metadata related to tiled map.
 */
class TiledMapMetadata constructor(val mapPath: String,
                                   val actorTexturePath: String,
                                   val npcTextures: Map<Int, String>) {

    fun getActorTextures(): List<String> {
        val textures = mutableListOf<String>()
        textures.add(actorTexturePath)
        textures.addAll(npcTextures.values)

        return textures
    }
}