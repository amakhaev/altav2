package com.alta_v2.physics.task.moveFocusPoint

import com.alta_v2.physics.executionContext.TiledMapEngineContext
import com.alta_v2.physics.task.ExecutionChecker

/**
 * Provides the checker that indicates when focus point can be moved.
 */
class MoveFocusPointChecker : ExecutionChecker {
    /**
     * {@inheritDoc}
     */
    override fun canTaskBeExecuted(context: TiledMapEngineContext): Boolean {
        return !context.focusPointLocal.isReserved() && !context.focusPointGlobal.isReserved()
    }
}