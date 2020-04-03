package com.alta_v2.rendering;

import com.alta_v2.common.Destroyable;

/**
 * Provides the high level abstraction for components that can be rendered.
 */
public interface Renderer extends Destroyable {

    /**
     * Initializes the renderable component.
     */
    void init(ScreenState state);

    /**
     * Renders the component.
     *
     * @param delta - the time in seconds since the last render
     */
    void render(float delta, ScreenState state);
}
