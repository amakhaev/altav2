package com.alta_v2.physics.task;

/**
 * Provides the task to be performed by physic engine.
 */
public interface TiledMapTask {

    /**
     * Indicates if task is completed.
     */
    boolean isCompleted();

    /**
     * Acts the tasks that currently active.
     *
     * @param delta - the time in seconds since the last render.
     */
    void act(float delta);
}
