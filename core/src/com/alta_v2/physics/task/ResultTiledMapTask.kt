package com.alta_v2.physics.task

import com.alta_v2.physics.task.resultObserver.TaskResult

/**
 * Describes the tiled map task that returns result of execution.
 */
interface ResultTiledMapTask : TiledMapTask {
    /**
     * Gets the result of task execution.
     */
    fun getResult(): TaskResult
}