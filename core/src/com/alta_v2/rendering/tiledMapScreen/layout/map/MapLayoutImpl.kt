package com.alta_v2.rendering.tiledMapScreen.layout.map

import com.alta_v2.rendering.ScreenState
import com.alta_v2.rendering.tiledMapScreen.TiledMapState
import com.alta_v2.utils.TILE_HEIGHT
import com.alta_v2.utils.TILE_WIDTH
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TiledMapRenderer
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.google.inject.assistedinject.Assisted
import com.google.inject.assistedinject.AssistedInject
import mu.KotlinLogging

/**
 * Provides the implementation of map layout.
 */
class MapLayoutImpl @AssistedInject constructor(@param:Assisted private val assetManager: AssetManager,
                                                @param:Assisted private val mapPath: String) : MapLayout {

    private val log = KotlinLogging.logger {  }

    private lateinit var tiledMap: TiledMap
    private lateinit var tiledMapRenderer: TiledMapRenderer
    private lateinit var camera: OrthographicCamera

    companion object {
        private val BOTTOM_LAYERS = intArrayOf(0, 1)
        private val TOP_LAYERS = intArrayOf(2, 3)
    }

    /**
     * {@inheritDoc}
     */
    override fun init(state: ScreenState) {
        tiledMap = assetManager.get(mapPath, TiledMap::class.java)
        tiledMapRenderer = OrthogonalTiledMapRenderer(tiledMap)
        camera = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())

        if (state is TiledMapState) {
            camera.position.x = state.mapCoordinates.x
            camera.position.y = state.mapCoordinates.y
        } else {
            log.error("ScreeState has invalid type, expected: ${TiledMapState::class.java}, found ${state::class.java}")
        }
    }

    /**
     * {@inheritDoc}
     */
    override fun applyState(state: ScreenState) {
        if (state is TiledMapState) {
            camera.position.x = state.mapCoordinates.x
            camera.position.y = state.mapCoordinates.y
        } else {
            log.error("ScreeState has invalid type, expected: ${TiledMapState::class.java}, found ${state::class.java}")
        }
    }

    /**
     * {@inheritDoc}
     */
    override fun renderBottomPart() {
        camera.update()
        tiledMapRenderer.setView(camera)
        tiledMapRenderer.render(BOTTOM_LAYERS)
    }

    /**
     * {@inheritDoc}
     */
    override fun renderTopPart() {
        camera.update()
        tiledMapRenderer.setView(camera)
        tiledMapRenderer.render(TOP_LAYERS)
    }

    override fun getTileWidth(): Int = tiledMap.properties.get(TILE_WIDTH, Int::class.java)

    override fun getTileHeight(): Int = tiledMap.properties.get(TILE_HEIGHT, Int::class.java)

    /**
     * {@inheritDoc}
     */
    override fun destroy() {
        tiledMap.dispose()
    }
}