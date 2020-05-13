package com.alta_v2.rendering.common.component.dialog

import com.alta_v2.rendering.Renderer
import com.alta_v2.rendering.ScreenState
import com.alta_v2.rendering.common.component.ComponentFactory
import com.alta_v2.rendering.common.component.animation.FadeAnimation
import com.alta_v2.rendering.common.component.animation.TranslationAnimation
import com.alta_v2.rendering.common.component.box.BoxComponent
import com.alta_v2.rendering.common.component.box.BoxStyle
import com.alta_v2.rendering.common.component.text.TextComponent
import com.alta_v2.rendering.common.component.text.TextStyle
import com.google.inject.assistedinject.Assisted
import com.google.inject.assistedinject.AssistedInject

class DialogComponent @AssistedInject constructor(@Assisted("fadeIn") fadeIn: TranslationAnimation,
                                                  @Assisted("fadeOut") fadeOut: TranslationAnimation,
                                                  @Assisted textFadeAnimation: FadeAnimation,
                                                  @Assisted boxStyle: BoxStyle,
                                                  @Assisted textStyle: TextStyle,
                                                  componentFactory: ComponentFactory) : Renderer {

    private val boxComponent: BoxComponent = componentFactory.createBoxComponent(boxStyle)
    private val textComponent: TextComponent = componentFactory.createTextComponent(textStyle)
    private val textFadeIn: FadeAnimation = textFadeAnimation
    private val boxFadeIn: TranslationAnimation = fadeIn
    private val boxFadeOut: TranslationAnimation = fadeOut

    private var currentBoxAnimation: TranslationAnimation? = null
    private var currentTextFade: FadeAnimation? = null
    private var text: String = ""
    private var isAnimated: Boolean = false

    var visible = false
        private set

    init {
        boxFadeIn.setCompleteListener(Runnable { onBoxFadeInComplete() })
    }

    override fun init(state: ScreenState) {
        boxComponent.init(state)
        textComponent.init(state)
    }

    override fun render(delta: Float, state: ScreenState) {
        if (currentBoxAnimation != null) {
            currentBoxAnimation!!.act(delta)
            boxComponent.setPosition(currentBoxAnimation!!.getX(), currentBoxAnimation!!.getY())
            boxComponent.render(delta, state)
        }
        if (currentTextFade != null) {
            currentTextFade!!.act(delta)
            textComponent.setAlpha(currentTextFade!!.getAlpha())
            textComponent.render(delta, state)
        }
    }

    override fun destroy() {
        boxComponent.destroy()
        textComponent.destroy()
        boxFadeIn.destroy()
        boxFadeOut.destroy()
        textFadeIn.destroy()
        currentBoxAnimation = null
        currentTextFade = null
    }

    fun show(message: String?, isAnimated: Boolean) {
        // runs box animation
        this.isAnimated = isAnimated
        currentBoxAnimation = boxFadeIn
        currentBoxAnimation!!.reset()
        textComponent.setText("", false)
        text = message ?: ""
        visible = true
    }

    fun hide() {
        textComponent.setText("", false)
        currentBoxAnimation = boxFadeOut
        currentBoxAnimation!!.reset()
        visible = false
    }

    private fun onBoxFadeInComplete() {
        textComponent.setText(text, isAnimated)

        // runs text animation
        currentTextFade = textFadeIn
        currentTextFade!!.reset()
    }
}