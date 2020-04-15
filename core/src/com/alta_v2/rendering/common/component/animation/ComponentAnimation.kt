package com.alta_v2.rendering.common.component.animation

import com.alta_v2.common.Destroyable

interface ComponentAnimation : Destroyable {
    /**
     * Acts the step in the animation.
     *
     * @param delta - the time in seconds between rendering.
     */
    fun act(delta: Float)

    /**
     * Resets the animation process.
     */
    fun reset()
}