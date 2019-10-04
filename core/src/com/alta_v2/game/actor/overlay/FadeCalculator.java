package com.alta_v2.game.actor.overlay;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Provides the descriptor for fade in transition.
 */
@RequiredArgsConstructor
class FadeCalculator {

    private final float duration;
    private final OverlayAnimationType overlayAnimationType;

    private float currentDuration;

    @Getter
    private float alpha;

    @Getter
    private boolean completed;

    /**
     * Acts the step in the calculation.
     *
     * @param delta - the delta between rendering.
     */
    void act(float delta) {
        switch (this.overlayAnimationType) {
            case FADE_IN:
                this.calculateFadeIn(delta);
                break;
            case FADE_OUT:
                this.calculateFadeOut(delta);
                break;
        }
    }

    private void calculateFadeIn(float delta) {
        if (this.completed) {
            return;
        }

        this.currentDuration += delta;

        if (this.currentDuration >= duration) {
            this.alpha = 0.0f;
            this.completed = true;
            return;
        }

        // calculates the percentage of current time
        float currentPercentage = this.duration / this.currentDuration;

        // calculates the alpha value by given percentage.
        this.alpha = 1.0f - 1.0f / currentPercentage;
        if (this.alpha <= 0.0f) {
            this.alpha = 0.0f;
            this.completed = true;
        }
    }

    private void calculateFadeOut(float delta) {
        if (this.completed) {
            return;
        }

        this.currentDuration += delta;

        if (this.currentDuration >= duration) {
            this.alpha = 1.0f;
            this.completed = true;
            return;
        }

        // calculates the percentage of current time
        float currentPercentage = this.duration / this.currentDuration;

        // calculates the alpha value by given percentage.
        this.alpha = 1.0f / currentPercentage;

        if (this.alpha >= 1.0f) {
            this.alpha = 1.0f;
            this.completed = true;
        }
    }
}
