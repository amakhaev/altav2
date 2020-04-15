package com.alta_v2.rendering.tiledMapScreen.layout.person

import com.alta_v2.rendering.ScreenState
import com.alta_v2.rendering.tiledMapScreen.TiledMapState
import com.alta_v2.rendering.tiledMapScreen.actors.player.PlayerActor
import com.alta_v2.rendering.tiledMapScreen.layout.ObjectLayout
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.google.inject.assistedinject.Assisted
import com.google.inject.assistedinject.AssistedInject
import mu.KotlinLogging

/**
 * Provides the layout that responsible to render player layer.
 */
class PlayerLayout @AssistedInject constructor(@param:Assisted private val assetManager: AssetManager,
                                               @param:Assisted private val texturePath: String) : ObjectLayout {

    private val log = KotlinLogging.logger {  }

    private lateinit var batch: SpriteBatch
    private lateinit var playerActor: PlayerActor

    override fun init(state: ScreenState, tileWidth: Int, tileHeight: Int) {
        batch = SpriteBatch()
        playerActor = PlayerActor(assetManager.get(texturePath, Texture::class.java), tileWidth, tileHeight)
    }

    /**
     * {@inheritDoc}
     */
    override fun init(state: ScreenState) {
        log.error("Not implemented yet")
    }

    /**
     * {@inheritDoc}
     */
    override fun render(delta: Float, state: ScreenState) {
        if (state is TiledMapState) {
            batch.begin()
            playerActor.draw(batch, delta, state.player)
            batch.end()
        } else {
            log.error("ScreeState has invalid type, expected: ${TiledMapState::class.java}, found ${state::class.java}")
        }
    }

    override fun destroy() {
        batch.dispose()
    }

}