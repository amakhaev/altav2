package com.alta_v2.rendering.tiledMapScreen.layout

import com.alta_v2.rendering.tiledMapScreen.layout.map.MapLayoutImpl
import com.alta_v2.rendering.tiledMapScreen.layout.person.NpcLayout
import com.alta_v2.rendering.tiledMapScreen.layout.person.PlayerLayout
import com.badlogic.gdx.assets.AssetManager

interface LayoutFactory {

    fun createPlayerLayout(assetManager: AssetManager, texturePath: String): PlayerLayout

    fun createNpcLayout(assetManager: AssetManager, npcTextures: Map<Int, String>): NpcLayout

    fun createMapLayout(assetManager: AssetManager, mapPath: String): MapLayoutImpl
}