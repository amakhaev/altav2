package com.alta_v2.rendering.common.component.animation

import com.badlogic.gdx.math.Vector2
import com.google.inject.assistedinject.Assisted

interface AnimationFactory {

    fun createFadeInAnimation(@Assisted(value = "start") start: Vector2,
                              @Assisted(value = "target") target: Vector2,
                              duration: Float): TranslationAnimation

    fun creteFadeAnimation(@Assisted("startAlpha") startAlpha: Float,
                           @Assisted("endAlpha") endAlpha: Float,
                           @Assisted("duration") duration: Float): FadeAnimation
}