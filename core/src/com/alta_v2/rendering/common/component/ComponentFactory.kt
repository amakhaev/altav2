package com.alta_v2.rendering.common.component

import com.alta_v2.rendering.common.component.animation.FadeAnimation
import com.alta_v2.rendering.common.component.animation.TranslationAnimation
import com.alta_v2.rendering.common.component.box.BoxComponent
import com.alta_v2.rendering.common.component.box.BoxStyle
import com.alta_v2.rendering.common.component.dialog.DialogComponent
import com.alta_v2.rendering.common.component.text.TextComponent
import com.alta_v2.rendering.common.component.text.TextStyle
import com.google.inject.assistedinject.Assisted

interface ComponentFactory {

    fun createBoxComponent(configuration: BoxStyle): BoxComponent

    fun createTextComponent(textStyle: TextStyle): TextComponent

    fun createDialogComponent(@Assisted("fadeIn") fadeIn: TranslationAnimation,
                              @Assisted("fadeOut") fadeOut: TranslationAnimation,
                              textFadeAnimation: FadeAnimation,
                              boxStyle: BoxStyle,
                              textStyle: TextStyle): DialogComponent
}