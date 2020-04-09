package com.alta_v2.rendering.common.component.animation;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import lombok.Getter;

/**
 * Provides the animation of fade in or fade out for component.
 */
public class FadeAnimation implements ComponentAnimation {

    private final float duration;
    private final float startAlpha;
    private final float endAlpha;

    private float currentExecutionTime = 0;

    @Getter
    private float alpha;

    @AssistedInject
    public FadeAnimation(@Assisted("startAlpha") float startAlpha,
                         @Assisted("endAlpha") float endAlpha,
                         @Assisted("duration") float duration) {
        this.duration = duration;
        this.startAlpha = startAlpha;
        this.endAlpha = endAlpha;
        alpha = startAlpha;
    }

    @Override
    public void act(float delta) {
        if (isCompleted()) {
            return;
        }

        currentExecutionTime += delta;

        if (this.currentExecutionTime >= duration) {
            alpha = endAlpha;
        } else {
            // calculates the percentage of current time
            float currentPercentage = this.currentExecutionTime / duration * 100;
            alpha = startAlpha + (endAlpha - startAlpha) / 100 * currentPercentage;
        }
    }

    @Override
    public void reset() {
        alpha = startAlpha;
        currentExecutionTime = 0;
    }

    @Override
    public void destroy() {
    }

    private boolean isCompleted() {
        return this.currentExecutionTime >= duration;
    }
}
