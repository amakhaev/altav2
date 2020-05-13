package com.alta_v2.rendering.common.component.dialog

import com.alta_v2.rendering.common.component.animation.FadeAnimation
import com.alta_v2.rendering.common.component.animation.TranslationAnimation
import com.alta_v2.rendering.common.component.box.BoxStyle
import com.alta_v2.rendering.common.component.text.TextStyle

interface StyleProvider {

    fun createBoxStyle(): BoxStyle

    fun createTextStyle(): TextStyle

    fun createBoxInAnimation(): TranslationAnimation

    fun createTextFadeInAnimation(): FadeAnimation

    fun createBoxOutAnimation(): TranslationAnimation

}