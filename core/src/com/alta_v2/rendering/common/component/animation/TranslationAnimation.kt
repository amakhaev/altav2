package com.alta_v2.rendering.common.component.animation

import com.badlogic.gdx.math.Vector2
import com.google.inject.assistedinject.Assisted
import com.google.inject.assistedinject.AssistedInject

/**
 * Provides the translation animation between two different points.
 */
class TranslationAnimation @AssistedInject constructor(@Assisted(value = "start") start: Vector2,
                                                       @Assisted(value = "target") target: Vector2,
                                                       @param:Assisted private val animationTime: Float) : ComponentAnimation {
    private val startX: Float = start.x
    private val startY: Float = start.y
    private val targetX: Float
    private val targetY: Float

    private var currentExecutionTime = 0f
    private var x: Float
    private var y: Float
    private var completeListener: Runnable? = null

    init {
        x = start.x
        y = start.y
        targetX = target.x
        targetY = target.y
    }

    fun getX() = x
    fun getY() = y

    fun setCompleteListener(listener: Runnable?) {
        completeListener = listener
    }

    override fun act(delta: Float) {
        if (isCompleted) {
            return
        }
        currentExecutionTime += delta
        if (currentExecutionTime >= animationTime) {
            x = targetX
            y = targetY
            if (completeListener != null) {
                completeListener!!.run()
            }
        } else {
            // calculates the percentage of current time
            val currentPercentage = currentExecutionTime / animationTime * 100
            x = startX + (targetX - startX) / 100 * currentPercentage
            y = startY + (targetY - startY) / 100 * currentPercentage
        }
    }

    override fun reset() {
        x = startX
        y = startY
        currentExecutionTime = 0f
    }

    override fun destroy() {
        completeListener = null
    }

    private val isCompleted: Boolean
        get() = currentExecutionTime >= animationTime
}