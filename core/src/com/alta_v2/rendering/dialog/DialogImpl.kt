package com.alta_v2.rendering.dialog

import com.alta_v2.rendering.Renderer
import com.alta_v2.rendering.ScreenState
import com.alta_v2.rendering.common.component.ComponentFactory
import com.alta_v2.rendering.common.component.dialog.TitleDialogStyleProvider
import com.google.inject.Inject
import lombok.extern.log4j.Log4j2
import mu.KotlinLogging

@Log4j2
class DialogImpl @Inject constructor(componentFactory: ComponentFactory, styleProvider: TitleDialogStyleProvider) : Renderer, TitleDialog {

    private val log = KotlinLogging.logger {  }

    private val titleDialog = componentFactory.createDialogComponent(
            styleProvider.createBoxInAnimation(), styleProvider.createBoxOutAnimation()
    )
    private var titleDialogDuration = 0f
    private var currentTitleDialogDuration = 0f

    override fun init(state: ScreenState) {
        titleDialog.init(state)
    }

    override fun render(delta: Float, state: ScreenState) {
        if (titleDialog.visible) {
            currentTitleDialogDuration += delta
            if (currentTitleDialogDuration >= titleDialogDuration) {
                hideTitle()
            }
        }
        titleDialog.render(delta, state)
    }

    override fun destroy() {
        titleDialog.destroy()
    }

    override fun showTitle(text: String, durationMs: Float) {
        if (titleDialog.visible) {
            log.warn("Title dialog already shown. Message '{}' won't shows", text)
            return
        }
        titleDialog.show(text)
        titleDialogDuration = durationMs / 1000
        currentTitleDialogDuration = 0f
    }

    override fun hideTitle() {
        if (titleDialog.visible) {
            titleDialog.hide()
        }
    }
}