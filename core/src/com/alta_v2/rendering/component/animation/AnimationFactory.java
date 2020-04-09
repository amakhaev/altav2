package com.alta_v2.rendering.component.animation;

import com.badlogic.gdx.math.Vector2;
import com.google.inject.assistedinject.Assisted;

public interface AnimationFactory {

    TranslationAnimation createFadeInAnimation(@Assisted(value = "start") Vector2 start,
                                               @Assisted(value = "target") Vector2 target,
                                               float duration);

    FadeAnimation creteFadeAnimation(@Assisted("startAlpha") float startAlpha,
                                     @Assisted("endAlpha") float endAlpha,
                                     @Assisted("duration") float duration);

}
