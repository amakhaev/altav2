package com.alta_v2.mediator.serde;

import com.alta_v2.rendering.ScreenState;

/**
 * Provides the high level abstraction for update screen state.
 */
public interface Updater {

    /**
     * Initializes the updater.
     */
    void init(ScreenState screenState);

    /**
     * Updates the screen.
     *
     * @param delta - the time in seconds since the last render.
     */
    void update(float delta, ScreenState screenState);

    /**
     * Destroys the updater.
     */
    void destroy();
}
