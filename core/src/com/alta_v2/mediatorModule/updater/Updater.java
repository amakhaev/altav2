package com.alta_v2.mediatorModule.updater;

/**
 * Provides the high level abstraction for update screen state.
 */
public interface Updater {

    /**
     * Updates the screen.
     *
     * @param delta - the time in seconds since the last render.
     */
    void update(float delta);

    /**
     * Destroys the updater.
     */
    void destroy();
}
