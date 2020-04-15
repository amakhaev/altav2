package com.alta_v2.physics.task.movePlayer

import com.alta_v2.physics.executionContext.TiledMapEngineContext
import com.alta_v2.physics.task.ExecutionChecker
import com.alta_v2.physics.task.moveFocusPoint.MoveFocusPointChecker

/**
 * Provides the checker to indicate when player could be moved to another point
 */
class MovePlayerChecker : ExecutionChecker {
    override fun canTaskBeExecuted(context: TiledMapEngineContext): Boolean {
        return MoveFocusPointChecker().canTaskBeExecuted(context)
    }
}