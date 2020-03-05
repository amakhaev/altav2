package com.alta_v2.physics.task.moveFocusPoint;

import com.alta_v2.physics.executionContext.TiledMapEngineContext;
import com.alta_v2.physics.task.ExecutionChecker;

/**
 * Provides the checker that indicates when focus point can be moved.
 */
public class MoveFocusPointChecker implements ExecutionChecker {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canTaskBeExecuted(TiledMapEngineContext context) {
        return !context.getFocusPointLocal().isReserved() && !context.getFocusPointGlobal().isReserved();
    }
}
