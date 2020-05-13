package com.alta_v2.rendering.common.component.text

import com.alta_v2.rendering.common.component.style.HorizontalAlign
import com.alta_v2.rendering.common.component.style.VerticalAlign
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import java.util.*

internal class TextRenderCalculator(private val style: TextStyle) {

    private val endLineSymbol = "\n"

    private val textWidth = style.renderAreaSize.x - style.renderAreaStart.x - style.textMargin
    var x = 0f
        private set

    var y = 0f
        private set

    fun formatText(text: String, font: BitmapFont): String {
        if (text.isEmpty()) {
            return text
        }

        val textAsWords = text.split("\\s".toRegex())
        return joinStringsUsingLineSeparator(textAsWords, font)
    }

    fun updateCoordinates(text: String, font: BitmapFont) {
        val glyphLayout = GlyphLayout()
        glyphLayout.setText(font, text)
        x = calculateX(glyphLayout.width)
        y = calculateY(glyphLayout.height)
    }

    private fun calculateX(textWidth: Float): Float {
        return when (style.hAlign) {
            HorizontalAlign.CENTER -> style.renderAreaStart.x + style.renderAreaSize.x / 2 - textWidth / 2
            HorizontalAlign.RIGHT -> style.renderAreaStart.x + style.renderAreaSize.x - textWidth - style.textMargin
            HorizontalAlign.LEFT -> style.renderAreaStart.x + + style.textMargin
        }
    }

    private fun calculateY(textHeight: Float): Float {
        return when (style.vAlign) {
            VerticalAlign.TOP -> style.renderAreaStart.y + style.renderAreaSize.y - style.textMargin
            VerticalAlign.CENTER -> style.renderAreaStart.y + style.renderAreaSize.y / 2 + textHeight / 2
            VerticalAlign.BOTTOM -> style.renderAreaStart.y + style.textMargin
        }
    }

    private fun joinStringsUsingLineSeparator(words: List<String>, font: BitmapFont): String {
        val glyphLayout = GlyphLayout()
        val lines: Stack<String> = Stack()
        words.forEach {
            if (lines.isEmpty()) {
                lines.push(it)
            } else {
                val lastLine = lines.pop()
                val enrichedLastLine = "$lastLine $it"
                glyphLayout.setText(font, enrichedLastLine)

                if (glyphLayout.width > textWidth) {
                    lines.push(lastLine)
                    lines.push(it)
                } else {
                    lines.push(enrichedLastLine)
                }
            }
        }

        return lines.joinToString(endLineSymbol)
    }
}