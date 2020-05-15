package com.alta_v2.rendering.common.component.text

import com.alta_v2.game.utils.Resources
import com.alta_v2.rendering.Renderer
import com.alta_v2.rendering.ScreenState
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter
import com.badlogic.gdx.utils.Align
import com.google.inject.assistedinject.Assisted
import com.google.inject.assistedinject.AssistedInject

class TextComponent @AssistedInject constructor(@param:Assisted private val textStyle: TextStyle) : Renderer {

    private val calculator = TextRenderCalculator(textStyle)
    private lateinit var ruFont: BitmapFont
    private lateinit  var batch: SpriteBatch

    private var currentText = ""
    private var signsToBeShown = 0

    val isAnimationCompleted: Boolean
        get() = signsToBeShown >= currentText.length

    override fun init(state: ScreenState) {
        batch = SpriteBatch()
        ruFont = generateFont()
    }

    override fun render(delta: Float, state: ScreenState) {
        if (signsToBeShown < currentText.length) {
            signsToBeShown++
        }

        batch.begin()
        ruFont.draw(batch, currentText.substring(0, signsToBeShown), calculator.x, calculator.y)
        batch.end()
    }

    override fun destroy() {
        batch.dispose()
        ruFont.dispose()
    }

    fun setText(text: String, isAnimated: Boolean) {
        currentText = calculator.formatText(text, ruFont)
        signsToBeShown = if (isAnimated) { 0 } else { this.currentText.length }
        calculator.updateCoordinates(currentText, ruFont)
    }

    fun setAlpha(alpha: Float) {
        if (ruFont.color.a != alpha) {
            ruFont.color.a = alpha
        }
    }

    fun completeAnimationImmediately() {
        signsToBeShown = currentText.length
    }

    private fun generateFont(): BitmapFont {

        // Configure font parameters
        val parameter = FreeTypeFontParameter()
        parameter.characters = Resources.RUSSIAN_CHARACTERS
        parameter.size = textStyle.textSize
        parameter.color = textStyle.color

        // Generate font
        val generator = FreeTypeFontGenerator(Gdx.files.internal(Resources.RU_FONT_NAME))
        val font = generator.generateFont(parameter)

        // Dispose resources
        generator.dispose()
        return font
    }
}