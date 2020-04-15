package com.alta_v2.rendering.common.component.dialog

import com.alta_v2.rendering.Renderer
import com.alta_v2.rendering.ScreenState
import com.alta_v2.rendering.common.component.ComponentFactory
import com.alta_v2.rendering.common.component.animation.FadeAnimation
import com.alta_v2.rendering.common.component.animation.TranslationAnimation
import com.alta_v2.rendering.common.component.box.BoxComponent
import com.alta_v2.rendering.common.component.text.TextComponent
import com.google.inject.assistedinject.Assisted
import com.google.inject.assistedinject.AssistedInject
import lombok.Getter

class DialogComponent @AssistedInject constructor(@Assisted("fadeIn") fadeIn: TranslationAnimation,
                                                  @Assisted("fadeOut") fadeOut: TranslationAnimation,
                                                  styleProvider: TitleDialogStyleProvider,
                                                  componentFactory: ComponentFactory) : Renderer {
    private val boxComponent: BoxComponent
    private val textComponent: TextComponent
    private val boxFadeIn: TranslationAnimation
    private val boxFadeOut: TranslationAnimation
    private val textFadeIn: FadeAnimation?
    private var currentBoxAnimation: TranslationAnimation? = null
    private var currentTextFade: FadeAnimation? = null
    private var text: String = ""

    var visible = false
        private set

    init {
        val boxStyle = styleProvider.createBoxStyle()
        boxComponent = componentFactory.createBoxComponent(boxStyle)
        textComponent = componentFactory.createTextComponent(styleProvider.createTextStyle())
        textFadeIn = styleProvider.createTextFadeInAnimation()
        boxFadeOut = fadeOut
        boxFadeIn = fadeIn
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
        textFadeIn!!.destroy()
        currentBoxAnimation = null
        currentTextFade = null
    }

    fun show(message: String?) {
        // runs box animation
        currentBoxAnimation = boxFadeIn
        currentBoxAnimation!!.reset()
        textComponent.setText("")
        text = message ?: ""
        visible = true
    }

    fun hide() {
        textComponent.setText("")
        currentBoxAnimation = boxFadeOut
        currentBoxAnimation!!.reset()
        visible = false
    }

    private fun onBoxFadeInComplete() {
        textComponent.setText(text)

        // runs text animation
        currentTextFade = textFadeIn
        currentTextFade!!.reset()
    }
}