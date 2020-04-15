package com.alta_v2.game.overlay

import com.badlogic.gdx.utils.Disposable

/**
 * Provides the actor that will draw black overlay on full screen.
 */
interface OverlayComponent : Disposable {
    /**
     * Acts the calculation of overlays panel.
     *
     * @param delta - the delta between rendering.
     */
    fun act(delta: Float)

    /**
     * Render the overlay panel.
     *
     * @param width - the width of overlay.
     * @param height - the height of overlay.
     */
    fun render(width: Float, height: Float)

    /**
     * Shows the overlay.
     *
     * @param duration - the duration of animation.
     */
    fun show(duration: Float)

    /**
     * Hides the overlay.
     *
     * @param duration      - the duration of animation.
     * @param postAction    - the post actions.
     */
    fun hide(duration: Float, postAction: Runnable?)
}