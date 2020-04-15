package com.alta_v2.physics.task

import com.alta_v2.common.Destroyable

/**
 * Provides the task to be performed by physic engine.
 */
interface TiledMapTask : Destroyable {
    /**
     * Indicates if task is completed.
     */
    fun isCompleted(): Boolean

    /**
     * Acts the tasks that currently active.
     *
     * @param delta - the time in seconds since the last render.
     */
    fun act(delta: Float)
}