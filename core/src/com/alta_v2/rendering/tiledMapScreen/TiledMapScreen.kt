package com.alta_v2.rendering.tiledMapScreen

import com.alta_v2.rendering.Renderer
import com.alta_v2.rendering.ScreenState
import com.alta_v2.rendering.tiledMapScreen.layout.LayoutFactory
import com.alta_v2.rendering.tiledMapScreen.layout.ObjectLayout
import com.alta_v2.utils.AssetLoader.Companion.load
import com.google.inject.assistedinject.Assisted
import com.google.inject.assistedinject.AssistedInject
import lombok.extern.log4j.Log4j2
import java.util.function.Consumer

/**
 * Provides the screen that shows a tiled map.
 */
@Log4j2
class TiledMapScreen @AssistedInject constructor(@Assisted metadata: TiledMapMetadata, layoutFactory: LayoutFactory) : Renderer {

    private val assetManager = load(metadata.mapPath, metadata.getActorTextures())
    private val mapLayout = layoutFactory.createMapLayout(assetManager, metadata.mapPath)
    private val contentLayouts = listOf(
            layoutFactory.createPlayerLayout(assetManager, metadata.actorTexturePath),
            layoutFactory.createNpcLayout(assetManager, metadata.npcTextures)
    )

    /**
     * {@inheritDoc}
     */
    override fun init(state: ScreenState) {
        mapLayout.init(state)
        contentLayouts.forEach { layout: ObjectLayout -> layout.init(state, mapLayout.getTileWidth(), mapLayout.getTileHeight()) }
    }

    /**
     * {@inheritDoc}
     */
    override fun render(delta: Float, state: ScreenState) {
        mapLayout.applyState(state)
        mapLayout.renderBottomPart()
        for (objectLayout in contentLayouts) {
            objectLayout.render(delta, state)
        }
        mapLayout.renderTopPart()
    }

    override fun destroy() {
        contentLayouts.forEach(Consumer { obj: ObjectLayout -> obj.destroy() })
        mapLayout.destroy()
        assetManager.dispose()
    }
}