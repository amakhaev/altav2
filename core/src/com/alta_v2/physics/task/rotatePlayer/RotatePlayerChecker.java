package com.alta_v2.physics.task.rotatePlayer;

import com.alta_v2.physics.executionContext.TiledMapEngineContext;
import com.alta_v2.physics.task.ExecutionChecker;
import com.alta_v2.physics.task.movePlayer.MovePlayerChecker;

/**
 * Provides the checker that indicates when player can be rotate.
 */
public class RotatePlayerChecker implements ExecutionChecker {

    @Override
    public boolean canTaskBeExecuted(TiledMapEngineContext context) {
        return new MovePlayerChecker().canTaskBeExecuted(context);
    }
}
