package com.alta_v2.physics.task

import com.alta_v2.physics.executionContext.TiledMapEngineContext

/**
 * Provides the checker to verify that task available to be executed.
 */
interface ExecutionChecker {
    /**
     * Indicates if task could be executed.
     *
     * @return true if requirements for task execution is satisfied, false otherwise
     */
    fun canTaskBeExecuted(context: TiledMapEngineContext): Boolean
}