package com.alta_v2.rendering.common.component.animation

import com.google.inject.assistedinject.Assisted
import com.google.inject.assistedinject.AssistedInject

/**
 * Provides the animation of fade in or fade out for component.
 */
class FadeAnimation @AssistedInject constructor(@param:Assisted("startAlpha") private val startAlpha: Float,
                                                @param:Assisted("endAlpha") private val endAlpha: Float,
                                                @param:Assisted("duration") private val duration: Float) : ComponentAnimation {
    private var currentExecutionTime = 0f

    private var alpha: Float

    init {
        alpha = startAlpha
    }

    fun getAlpha() = alpha

    override fun act(delta: Float) {
        if (isCompleted) {
            return
        }
        currentExecutionTime += delta
        alpha = if (currentExecutionTime >= duration) {
            endAlpha
        } else {
            // calculates the percentage of current time
            val currentPercentage = currentExecutionTime / duration * 100
            startAlpha + (endAlpha - startAlpha) / 100 * currentPercentage
        }
    }

    override fun reset() {
        alpha = startAlpha
        currentExecutionTime = 0f
    }

    override fun destroy() {}

    private val isCompleted: Boolean
        get() = currentExecutionTime >= duration
}