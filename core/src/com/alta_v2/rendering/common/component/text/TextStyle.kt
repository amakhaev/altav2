package com.alta_v2.rendering.common.component.text

import com.alta_v2.rendering.common.component.style.HorizontalAlign
import com.alta_v2.rendering.common.component.style.VerticalAlign
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2

data class TextStyle(val color: Color,
                     val renderAreaStart: Vector2,
                     val renderAreaSize: Vector2,
                     val hAlign: HorizontalAlign,
                     val vAlign: VerticalAlign,
                     val textSize: Int)