package com.alta_v2.rendering.common.component.box

import com.alta_v2.rendering.Renderer
import com.alta_v2.rendering.ScreenState
import com.alta_v2.rendering.common.utils.gradients
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.google.inject.assistedinject.Assisted
import com.google.inject.assistedinject.AssistedInject

class BoxComponent @AssistedInject constructor(@Assisted private val style: BoxStyle) : Renderer {

    private lateinit var boxRenderer: ShapeRenderer
    private lateinit var borderRenderer: ShapeRenderer
    private val calculator = BoxRenderCalculator(style)

    override fun init(state: ScreenState) {
        boxRenderer = ShapeRenderer()
        boxRenderer.setAutoShapeType(true)
        boxRenderer.color = style.boxColor

        borderRenderer = ShapeRenderer()
        borderRenderer.setAutoShapeType(true)
        borderRenderer.color = style.borderColor
    }

    override fun render(delta: Float, state: ScreenState) {
        Gdx.gl.glEnable(GL20.GL_BLEND)
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA)

        boxRenderer.begin(ShapeRenderer.ShapeType.Filled)
        boxRenderer.rect(calculator.boxX, calculator.boxY, calculator.boxWidth.toFloat(), calculator.boxHeight.toFloat())
        boxRenderer.end()
        if (style.useBorder) {
            renderBorder()
        }
    }

    override fun destroy() {
        boxRenderer.dispose()
        borderRenderer.dispose()
    }

    fun setPosition(x: Float, y: Float) {
        calculator.updatePosition(x, y)
    }

    private fun renderBorder() {
        val borderGradient = gradients[style.borderGradient]
        Gdx.gl.glLineWidth(calculator.borderThickness.toFloat())
        borderRenderer.begin(ShapeRenderer.ShapeType.Point)
        if (borderGradient != null) {
            borderRenderer.rect(
                    calculator.borderX, calculator.borderY,
                    calculator.borderWidth.toFloat(), calculator.borderHeight.toFloat(),
                    borderGradient[0], borderGradient[1], borderGradient[2], borderGradient[3]
            )
        } else {
            borderRenderer.rect(
                    calculator.borderX, calculator.borderY,
                    calculator.borderWidth.toFloat(), calculator.borderHeight
                    .toFloat())
        }
        borderRenderer.end()
        Gdx.gl.glDisable(GL20.GL_BLEND)
    }
}