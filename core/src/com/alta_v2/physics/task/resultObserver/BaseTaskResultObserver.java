package com.alta_v2.physics.task.resultObserver;

import lombok.Getter;

public class BaseTaskResultObserver implements TaskResultObserver {

    private Runnable completeHandler;

    @Getter
    private boolean completed;

    /**
     * {@inheritDoc}
     */
    @Override
    public void subscribeOnComplete(Runnable handler) {
        this.completeHandler = handler;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
        this.completeHandler = null;
    }

    /**
     * Submits complete event.
     */
    public void submitComplete() {
        try {
            if (this.completeHandler != null) {
                this.completeHandler.run();
            }
        } finally {
            this.completed = true;
        }
    }
}
