package com.alta_v2.rendering.common.utils

import com.badlogic.gdx.graphics.Color

enum class Gradient {
    ORANGE
}

val gradients = mapOf<Gradient, List<Color>>(
        Gradient.ORANGE to listOf(Color(-0x8bff56), Color(-0x59b4ff45), Color(-0x59b4ff56), Color(-0x69bf45))
)