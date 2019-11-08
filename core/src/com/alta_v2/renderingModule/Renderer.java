package com.alta_v2.renderingModule;

/**
 * Provides the high level abstraction for components that can be rendered.
 */
public interface Renderer {

    /**
     * Initializes the renderable component.
     */
    void init();

    /**
     * Renders the component.
     *
     * @param delta - the time in seconds since the last render
     */
    void render(float delta);

    /**
     * Disposes the component.
     */
    void dispose();
}
