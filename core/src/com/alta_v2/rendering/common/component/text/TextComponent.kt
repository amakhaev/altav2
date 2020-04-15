package com.alta_v2.rendering.common.component.text

import com.alta_v2.game.utils.Resources
import com.alta_v2.rendering.Renderer
import com.alta_v2.rendering.ScreenState
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter
import com.google.inject.assistedinject.Assisted
import com.google.inject.assistedinject.AssistedInject

class TextComponent @AssistedInject constructor(@param:Assisted private val textStyle: TextStyle) : Renderer {

    private val calculator = TextRenderCalculator(textStyle)
    private var ruFont: BitmapFont? = null
    private var batch: SpriteBatch? = null
    private var currentText = ""

    override fun init(state: ScreenState) {
        batch = SpriteBatch()
        ruFont = generateFont()
    }

    override fun render(delta: Float, state: ScreenState) {
        batch!!.begin()
        ruFont!!.draw(batch, currentText, calculator.x, calculator.y)
        batch!!.end()
    }

    override fun destroy() {
        batch!!.dispose()
        ruFont!!.dispose()
    }

    fun setText(currentText: String) {
        this.currentText = currentText
        calculator.updateCoordinates(currentText, ruFont)
    }

    fun setAlpha(alpha: Float) {
        if (ruFont!!.color.a != alpha) {
            ruFont!!.color.a = alpha
        }
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