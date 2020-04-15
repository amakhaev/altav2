package com.alta_v2.rendering.common.component.box

internal class BoxRenderCalculator(private val configuration: BoxStyle) {

    val boxWidth: Int
    val boxHeight: Int

    var boxX: Float
        private set

    var boxY: Float
        private set

    var borderX = 0f
        private set

    var borderY = 0f
        private set

    var borderWidth = 0
        private set

    var borderHeight = 0
        private set

    var borderThickness: Byte = 1
        private set

    init {
        boxX = configuration.boxX.toFloat()
        boxY = configuration.boxY.toFloat()
        boxWidth = configuration.boxWidth
        boxHeight = configuration.boxHeight
        borderThickness = configuration.borderThickness
        calculateBorder()
    }

    fun updatePosition(x: Float, y: Float) {
        boxX = x
        boxY = y
        calculateBorder()
    }

    private fun calculateBorder() {
        borderWidth = boxWidth + borderThickness
        borderHeight = boxHeight + borderThickness
        borderX = boxX - 1
        borderY = boxY - 1
    }
}