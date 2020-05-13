package com.alta_v2.rendering.common.component.dialog

import com.alta_v2.rendering.common.component.ComponentStyle
import com.alta_v2.rendering.common.component.animation.AnimationFactory
import com.alta_v2.rendering.common.component.animation.FadeAnimation
import com.alta_v2.rendering.common.component.animation.TranslationAnimation
import com.alta_v2.rendering.common.component.box.BoxStyle
import com.alta_v2.rendering.common.component.style.HorizontalAlign
import com.alta_v2.rendering.common.component.style.VerticalAlign
import com.alta_v2.rendering.common.component.text.TextStyle
import com.alta_v2.rendering.common.utils.Gradient
import com.alta_v2.rendering.config.AppConfig
import com.alta_v2.rendering.config.Theme
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.google.inject.Inject

class DialogStyleProvider @Inject constructor(private val appConfig: AppConfig,
                                              private val theme: Theme,
                                              private val componentStyle: ComponentStyle,
                                              private val animationFactory: AnimationFactory) : StyleProvider {

    override fun createBoxStyle(): BoxStyle {
        val dialog = componentStyle.dialog
        val boxWidth = appConfig.width * dialog.boxWidthPercentage / 100
        val boxX = getDialogBoxX(boxWidth)
        val boxY = getDialogBoxY(dialog.boxHeight)

        return BoxStyle(
                boxWidth = boxWidth,
                boxHeight = dialog.boxHeight,
                boxX = boxX,
                boxY = boxY,
                boxColor = Color.valueOf(theme.box.color),
                useBorder = dialog.useBorder,
                borderThickness = dialog.borderThickness,
                borderColor = Color.valueOf(theme.box.borderColor),
                borderGradient = Gradient.ORANGE
        )
    }

    override fun createTextStyle(): TextStyle {
        val dialog = componentStyle.dialog
        val boxStyle = createBoxStyle()

        return TextStyle(
                color = Color.valueOf(theme.box.textColor),
                hAlign = dialog.textHAlign,
                vAlign = dialog.textVAlign,
                renderAreaStart = Vector2(boxStyle.boxX.toFloat(), boxStyle.boxY.toFloat()),
                renderAreaSize = Vector2(boxStyle.boxWidth.toFloat(), boxStyle.boxHeight.toFloat()),
                textSize = componentStyle.dialog.textSize,
                textMargin = dialog.textMargin
        )
    }

    override fun createBoxInAnimation(): TranslationAnimation {
        val boxStyle = createBoxStyle()

        return animationFactory.createFadeInAnimation(
                Vector2(boxStyle.boxX.toFloat(), -boxStyle.boxHeight.toFloat()),
                Vector2(boxStyle.boxX.toFloat(), boxStyle.boxY.toFloat()),
                componentStyle.dialog.fadeInTime / 1000
        )
    }

    override fun createBoxOutAnimation(): TranslationAnimation {
        val boxStyle = createBoxStyle()
        return animationFactory.createFadeInAnimation(
                Vector2(boxStyle.boxX.toFloat(), boxStyle.boxY.toFloat()),
                Vector2(boxStyle.boxX.toFloat(), -boxStyle.boxHeight.toFloat() - 5),
                componentStyle.dialog.fadeOutTime / 1000
        )
    }

    override fun createTextFadeInAnimation(): FadeAnimation =
            animationFactory.creteFadeAnimation(0f, 1f, componentStyle.dialog.fadeInTime / 1000)

    private fun getDialogBoxX(boxWidth: Int): Int {
        return when (componentStyle.dialog.boxHAlign) {
            HorizontalAlign.LEFT -> componentStyle.dialog.marginLeft
            HorizontalAlign.CENTER -> (appConfig.width - boxWidth) / 2
            HorizontalAlign.RIGHT -> appConfig.width - boxWidth + componentStyle.dialog.marginRight
        }
    }

    private fun getDialogBoxY(boxHeight: Int): Int {
        return when (componentStyle.dialog.boxVAlign) {
            VerticalAlign.TOP -> appConfig.height - boxHeight - componentStyle.dialog.marginTop
            VerticalAlign.CENTER -> (appConfig.height - boxHeight) / 2
            VerticalAlign.BOTTOM -> componentStyle.dialog.marginBottom
        }
    }
}