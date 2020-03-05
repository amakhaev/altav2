package com.alta_v2.physics.task.movePlayer;

import com.alta_v2.physics.executionContext.TiledMapEngineContext;
import com.alta_v2.physics.task.ExecutionChecker;
import com.alta_v2.physics.task.moveFocusPoint.MoveFocusPointChecker;

/**
 * Provides the checker to indicate when player could be moved to another point
 */
public class MovePlayerChecker implements ExecutionChecker {

    @Override
    public boolean canTaskBeExecuted(TiledMapEngineContext context) {
        return new MoveFocusPointChecker().canTaskBeExecuted(context);
    }
}
