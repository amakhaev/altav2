package com.alta_v2.game.overlay

/**
 * Provides the descriptor for fade in transition.
 */
internal class FadeCalculator {

    private var currentDuration = 0f

    var duration: Float = 0.5f
    var animationType: OverlayAnimationType = OverlayAnimationType.FADE_IN
    private var alpha = 0f
    private var completed = false

    /**
     * Acts the step in the calculation.
     *
     * @param delta - the delta between rendering.
     */
    fun act(delta: Float) {
        when (animationType) {
            OverlayAnimationType.FADE_IN -> calculateFadeIn(delta)
            OverlayAnimationType.FADE_OUT -> calculateFadeOut(delta)
        }
    }

    fun reset() {
        alpha = 0f
        currentDuration = 0f
        completed = false
    }

    fun isCompleted() = completed

    fun getAlpha() = alpha

    private fun calculateFadeIn(delta: Float) {
        if (completed) {
            return
        }
        currentDuration += delta
        if (currentDuration >= duration) {
            alpha = 0.0f
            completed = true
            return
        }

        // calculates the percentage of current time
        val currentPercentage = duration / currentDuration

        // calculates the alpha value by given percentage.
        alpha = 1.0f - 1.0f / currentPercentage
        if (alpha <= 0.0f) {
            alpha = 0.0f
            completed = true
        }
    }

    private fun calculateFadeOut(delta: Float) {
        if (completed) {
            return
        }
        currentDuration += delta
        if (currentDuration >= duration) {
            alpha = 1.0f
            completed = true
            return
        }

        // calculates the percentage of current time
        val currentPercentage = duration / currentDuration

        // calculates the alpha value by given percentage.
        alpha = 1.0f / currentPercentage
        if (alpha >= 1.0f) {
            alpha = 1.0f
            completed = true
        }
    }
}