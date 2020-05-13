package com.alta_v2.rendering.common.component.style

import kotlin.properties.Delegates

class DialogStyle {
    
    var fadeInTime by Delegates.notNull<Float>()    
    var fadeOutTime by Delegates.notNull<Float>()
    var boxWidthPercentage by Delegates.notNull<Int>()
    var boxHeight by Delegates.notNull<Int>()
    var boxHAlign: HorizontalAlign = HorizontalAlign.LEFT
    var boxVAlign: VerticalAlign = VerticalAlign.BOTTOM
    var marginTop: Int = 0
    var marginBottom: Int = 0
    var marginRight: Int = 0
    var marginLeft: Int = 0
    var useBorder: Boolean = false
    var borderThickness: Byte = 0
    var textHAlign: HorizontalAlign = HorizontalAlign.LEFT
    var textVAlign: VerticalAlign = VerticalAlign.BOTTOM
    var textSize: Int = 12
    var textMargin: Float = 0f
}