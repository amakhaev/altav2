package com.alta_v2.rendering.dialog

import com.alta_v2.rendering.common.component.ComponentFactory
import com.alta_v2.rendering.common.component.dialog.DialogStyleProvider
import com.alta_v2.rendering.common.component.dialog.StyleProvider
import com.alta_v2.rendering.common.component.dialog.TitleDialogStyleProvider
import com.google.inject.Inject

class DialogComponentFactory @Inject constructor(private val componentFactory: ComponentFactory,
                                                 private val titleDialogStyleProvider: TitleDialogStyleProvider,
                                                 private val dialogStyleProvider: DialogStyleProvider) {

    fun createTitleDialog() = create(titleDialogStyleProvider)

    fun createDialog() = create(dialogStyleProvider)

    private fun create(provider: StyleProvider) = componentFactory.createDialogComponent(
            fadeIn = provider.createBoxInAnimation(),
            fadeOut = provider.createBoxOutAnimation(),
            textFadeAnimation = provider.createTextFadeInAnimation(),
            boxStyle = provider.createBoxStyle(),
            textStyle = provider.createTextStyle()
    )
}