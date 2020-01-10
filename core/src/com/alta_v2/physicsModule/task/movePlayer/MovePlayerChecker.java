package com.alta_v2.physicsModule.task.movePlayer;

import com.alta_v2.physicsModule.executionContext.TiledMapEngineContext;
import com.alta_v2.physicsModule.task.ExecutionChecker;
import com.alta_v2.physicsModule.task.moveFocusPoint.MoveFocusPointChecker;

/**
 * Provides the checker to indicate when player could be moved to another point
 */
public class MovePlayerChecker implements ExecutionChecker {

    @Override
    public boolean canTaskBeExecuted(TiledMapEngineContext context) {
        return new MoveFocusPointChecker().canTaskBeExecuted(context);
    }
}
