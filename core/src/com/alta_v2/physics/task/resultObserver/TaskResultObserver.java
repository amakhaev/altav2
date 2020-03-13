package com.alta_v2.physics.task.resultObserver;

import com.alta_v2.common.Destroyable;

/**
 * Provides the result of task execution.
 */
public interface TaskResultObserver extends Destroyable {

    /**
     * Subscribes to task completion event.
     *
     * @param handler - the handler to be used for handle complete event.
     */
    void subscribeOnComplete(Runnable handler);

    /**
     * Indicates when task completed.
     */
    boolean isCompleted();

}
