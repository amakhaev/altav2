package com.alta_v2.physicsModule.task;

import com.alta_v2.physicsModule.executionContext.TiledMapEngineContext;

/**
 * Provides the checker to verify that task available to be executed.
 */
public interface ExecutionChecker {

    /**
     * Indicates if task could be executed.
     *
     * @return true if requirements for task execution is satisfied, false otherwise
     */
    boolean canTaskBeExecuted(TiledMapEngineContext context);

}
