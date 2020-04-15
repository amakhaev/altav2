package com.alta_v2.rendering.common.component.text

import com.alta_v2.rendering.common.component.style.HorizontalAlign
import com.alta_v2.rendering.common.component.style.VerticalAlign
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout

internal class TextRenderCalculator(private val style: TextStyle) {

    private val glyphLayout = GlyphLayout()

    var x = 0f
        private set

    var y = 0f
        private set

    fun updateCoordinates(text: String, font: BitmapFont?) {
        glyphLayout.setText(font, text)
        x = calculateX(glyphLayout.width)
        y = calculateY(glyphLayout.height)
    }

    private fun calculateX(textWidth: Float): Float {
        return when (style.hAlign) {
            HorizontalAlign.CENTER -> style.renderAreaStart.x + style.renderAreaSize.x / 2 - textWidth / 2
            HorizontalAlign.RIGHT -> style.renderAreaStart.x + style.renderAreaSize.x - textWidth
            HorizontalAlign.LEFT -> style.renderAreaStart.x
        }
    }

    private fun calculateY(textHeight: Float): Float {
        return when (style.vAlign) {
            VerticalAlign.TOP -> style.renderAreaStart.y + style.renderAreaSize.y - textHeight
            VerticalAlign.CENTER -> style.renderAreaStart.y + style.renderAreaSize.y / 2 + textHeight / 2
            VerticalAlign.BOTTOM -> style.renderAreaStart.y
        }
    }

}