package com.alta_v2.rendering.common.component.box

import com.alta_v2.rendering.common.utils.Gradient
import com.badlogic.gdx.graphics.Color

data class BoxStyle (val boxWidth: Int = 0,
                     val boxHeight: Int = 0,
                     val boxX: Int = 0,
                     val boxY: Int = 0,
                     val boxColor: Color = Color.BLACK,
                     val useBorder: Boolean = false,
                     val borderThickness: Byte = 0,
                     val borderColor: Color = Color.RED,
                     val borderGradient: Gradient? = null
)