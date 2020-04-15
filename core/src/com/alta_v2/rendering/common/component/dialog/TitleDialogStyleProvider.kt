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

class TitleDialogStyleProvider @Inject constructor(private val appConfig: AppConfig,
                                                   private val theme: Theme,
                                                   private val componentStyle: ComponentStyle,
                                                   private val animationFactory: AnimationFactory) {

    fun createBoxStyle(): BoxStyle {
        val titleDialog = componentStyle.titleDialog
        val boxWidth = appConfig.width * titleDialog.boxWidthPercentage / 100
        val boxX = getTitleDialogBoxX(boxWidth)
        val boxY = getTitleDialogBoxY(titleDialog.boxHeight)

        return BoxStyle(
                boxWidth = boxWidth,
                boxHeight = titleDialog.boxHeight,
                boxX = boxX,
                boxY = boxY,
                boxColor = Color.valueOf(theme.box.color),
                useBorder = titleDialog.useBorder,
                borderThickness = titleDialog.borderThickness,
                borderColor = Color.valueOf(theme.box.borderColor),
                borderGradient = Gradient.ORANGE
        )
    }

    fun createTextStyle(): TextStyle {
        val titleDialog = componentStyle.titleDialog
        val boxStyle = createBoxStyle()

        return TextStyle(
                color = Color.valueOf(theme.box.textColor),
                hAlign = titleDialog.textHAlign,
                vAlign = titleDialog.textVAlign,
                renderAreaStart = Vector2(boxStyle.boxX.toFloat(), boxStyle.boxY.toFloat()),
                renderAreaSize = Vector2(boxStyle.boxWidth.toFloat(), boxStyle.boxHeight.toFloat()),
                textSize = componentStyle.titleDialog.textSize
        )
    }

    fun createBoxInAnimation(): TranslationAnimation {
        val boxStyle = createBoxStyle()

        return animationFactory.createFadeInAnimation(
                Vector2(boxStyle.boxX.toFloat(), (boxStyle.boxY + 50).toFloat()),
                Vector2(boxStyle.boxX.toFloat(), boxStyle.boxY.toFloat()),
                componentStyle.titleDialog.fadeInTime / 1000
        )
    }

    fun createTextFadeInAnimation(): FadeAnimation {
        return animationFactory.creteFadeAnimation(0f, 1f, componentStyle.titleDialog.fadeInTime / 1000)
    }

    fun createBoxOutAnimation(): TranslationAnimation {
        val boxStyle = createBoxStyle()
        return animationFactory.createFadeInAnimation(
                Vector2(boxStyle.boxX.toFloat(), boxStyle.boxY.toFloat()),
                Vector2(boxStyle.boxX.toFloat(), (boxStyle.boxY + 50).toFloat()),
                componentStyle.titleDialog.fadeInTime / 1000
        )
    }

    private fun getTitleDialogBoxX(boxWidth: Int): Int {
        return when (componentStyle.titleDialog.boxHAlign) {
            HorizontalAlign.LEFT -> componentStyle.titleDialog.marginLeft
            HorizontalAlign.CENTER -> (appConfig.width - boxWidth) / 2
            HorizontalAlign.RIGHT -> appConfig.width - boxWidth + componentStyle.titleDialog.marginRight
        }
    }

    private fun getTitleDialogBoxY(boxHeight: Int): Int {
        return when (componentStyle.titleDialog.boxVAlign) {
            VerticalAlign.TOP -> appConfig.height - boxHeight - componentStyle.titleDialog.marginTop
            VerticalAlign.CENTER -> (appConfig.height - boxHeight) / 2
            VerticalAlign.BOTTOM -> componentStyle.titleDialog.marginBottom
        }
    }
}