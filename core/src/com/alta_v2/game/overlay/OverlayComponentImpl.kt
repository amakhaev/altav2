package com.alta_v2.game.overlay

import com.alta_v2.game.utils.Resources
import com.alta_v2.utils.AssetLoader
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.google.inject.assistedinject.AssistedInject

/**
 * Provides the actor that will draw black overlay on full screen.
 */
class OverlayComponentImpl @AssistedInject constructor() : OverlayComponent {

    private val assetManager = AssetLoader.load(Resources.TEXTURE_BLACK_SQUARE)
    private val blackSquareTexture = assetManager.get(Resources.TEXTURE_BLACK_SQUARE, Texture::class.java)
    private val batch: Batch = SpriteBatch()

    private var fadeCalculator: FadeCalculator = FadeCalculator()

    private var postAction: Runnable? = null

    /**
     * {@inheritDoc}
     */
    override fun act(delta: Float) {
        if (!fadeCalculator.isCompleted()) {
            fadeCalculator.act(delta)
        }
    }

    /**
     * {@inheritDoc}
     */
    override fun render(width: Float, height: Float) {
        batch.begin()
        batch.setColor(0f, 0f, 0f, fadeCalculator.getAlpha())
        batch.draw(blackSquareTexture, 0f, 0f, width, height)
        batch.end()
        if (fadeCalculator.isCompleted() && postAction != null) {
            postAction!!.run()
            postAction = null
        }
    }

    /**
     * {@inheritDoc}
     */
    override fun show(duration: Float) {
        fadeCalculator.duration = duration;
        fadeCalculator.animationType = OverlayAnimationType.FADE_IN
        fadeCalculator.reset()
    }

    /**
     * {@inheritDoc}
     */
    override fun hide(duration: Float, postAction: Runnable?) {
        fadeCalculator.duration = duration;
        fadeCalculator.animationType = OverlayAnimationType.FADE_OUT
        fadeCalculator.reset()
        this.postAction = postAction
    }

    /**
     * {@inheritDoc}
     */
    override fun dispose() {
        batch.dispose()
        blackSquareTexture.dispose()
        assetManager.dispose()
    }
}