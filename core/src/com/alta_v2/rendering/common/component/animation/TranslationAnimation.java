package com.alta_v2.rendering.common.component.animation;

import com.badlogic.gdx.math.Vector2;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import lombok.Getter;
import lombok.Setter;

/**
 * Provides the translation animation between two different points.
 */
public class TranslationAnimation implements ComponentAnimation {

    private final float animationTime;
    private final float startX;
    private final float startY;
    private final float targetX;
    private final float targetY;

    private float currentExecutionTime = 0;

    @Getter
    private float x;

    @Getter
    private float y;

    @Setter
    private Runnable completeListener;

    @AssistedInject
    public TranslationAnimation(@Assisted(value = "start") Vector2 start,
                                @Assisted(value = "target") Vector2 target,
                                @Assisted float duration) {
        this.animationTime = duration;
        startX = start.x;
        startY = start.y;
        x = start.x;
        y = start.y;

        targetX = target.x;
        targetY = target.y;
    }

    @Override
    public void act(float delta) {
        if (isCompleted()) {
            return;
        }

        currentExecutionTime += delta;

        if (this.currentExecutionTime >= animationTime) {
            x = targetX;
            y = targetY;
            if (completeListener != null) {
                completeListener.run();
            }
        } else {
            // calculates the percentage of current time
            float currentPercentage = this.currentExecutionTime / animationTime * 100;

            x = startX + (targetX - startX) / 100 * currentPercentage;
            y = startY + (targetY - startY) / 100 * currentPercentage;
        }
    }

    @Override
    public void reset() {
        x = startX;
        y = startY;
        currentExecutionTime = 0;
    }

    @Override
    public void destroy() {
        completeListener = null;
    }

    private boolean isCompleted() {
        return this.currentExecutionTime >= animationTime;
    }
}
