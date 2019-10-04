package com.alta_v2.game.actor.overlay;

import com.badlogic.gdx.utils.Disposable;

/**
 * Provides the actor that will draw black overlay on full screen.
 */
public interface OverlayActor extends Disposable {

    /**
     * Acts the calculation of overlays panel.
     *
     * @param delta - the delta between rendering.
     */
    void act(float delta);

    /**
     * Render the overlay panel.
     *
     * @param width - the width of overlay.
     * @param height - the height of overlay.
     */
    void render(float width, float height);

    /**
     * Shows the overlay.
     *
     * @param duration - the duration of animation.
     */
    void show(float duration);

    /**
     * Hides the overlay.
     *
     * @param duration      - the duration of animation.
     * @param postAction    - the post actions.
     */
    void hide(float duration, Runnable postAction);
}
