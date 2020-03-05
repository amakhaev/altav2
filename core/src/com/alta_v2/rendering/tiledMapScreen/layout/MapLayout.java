package com.alta_v2.rendering.tiledMapScreen.layout;

import com.alta_v2.rendering.ScreenState;

/**
 * Provides the layout to render a tiled map.
 */
public interface MapLayout {

    /**
     * Initializes the renderable component.
     */
    void init(ScreenState state);

    /**
     * Applies the screen state to layout.
     *
     * @param state - the state to be used for rendering.
     */
    void applyState(ScreenState state);

    /**
     * Renders the bottom part of tiled map.
     */
    void renderBottomPart();

    /**
     * Renders the top part of tiled map.
     */
    void renderTopPart();

    /**
     * Disposes the component.
     */
    void dispose();

}
