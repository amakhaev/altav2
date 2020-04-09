package com.alta_v2.rendering.component.animation;

import com.alta_v2.common.Destroyable;

public interface ComponentAnimation extends Destroyable {

    /**
     * Acts the step in the animation.
     *
     * @param delta - the time in seconds between rendering.
     */
    void act(float delta);

    /**
     * Resets the animation process.
     */
    void reset();

}
