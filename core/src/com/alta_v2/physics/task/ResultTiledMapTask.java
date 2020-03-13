package com.alta_v2.physics.task;

import com.alta_v2.physics.task.resultObserver.TaskResultObserver;

/**
 * Describes the tiled map task that returns result of execution.
 */
public interface ResultTiledMapTask extends TiledMapTask {

    /**
     * Gets the result of task execution.
     */
    TaskResultObserver getResult();

}
