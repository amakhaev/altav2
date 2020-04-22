package com.alta_v2.rendering.menuScreen

import com.alta_v2.game.utils.Resources
import com.alta_v2.rendering.Renderer
import com.alta_v2.rendering.ScreenState
import com.alta_v2.utils.AssetLoader.Companion.load
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.google.inject.assistedinject.AssistedInject

/**
 * Provides the menu screen.
 */
class MenuScreen @AssistedInject constructor() : Renderer {

    private lateinit var assetManager: AssetManager
    private lateinit var batch: Batch
    private lateinit var backgroundTexture: Texture

    /**
     * {@inheritDoc}
     */
    override fun init(state: ScreenState) {
        assetManager = load(Resources.TEXTURE_MENU_BACKGROUND)
        backgroundTexture = assetManager.get(Resources.TEXTURE_MENU_BACKGROUND, Texture::class.java)
        batch = SpriteBatch()
    }

    /**
     * {@inheritDoc}
     */
    override fun render(delta: Float, state: ScreenState) {
        batch.begin()
        batch.draw(backgroundTexture, 0f, 0f, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        batch.end()
    }

    /**
     * {@inheritDoc}
     */
    override fun destroy() {
        batch.dispose()
        backgroundTexture.dispose()
        assetManager.dispose()
    }
}