package com.alta_v2.rendering.dialog

import com.alta_v2.rendering.Renderer
import com.alta_v2.rendering.ScreenState
import com.alta_v2.rendering.common.component.dialog.DialogComponent
import com.google.inject.Inject
import mu.KotlinLogging

class DialogImpl @Inject constructor(dialogFactory: DialogComponentFactory) : Renderer, Dialog {

    private val log = KotlinLogging.logger {  }

    private val dialog = dialogFactory.createDialog()
    private val titleDialog = dialogFactory.createTitleDialog()

    private var titleDialogDuration = 0f
    private var currentTitleDialogDuration = 0f

    override fun init(state: ScreenState) {
        titleDialog.init(state)
        dialog.init(state)
    }

    override fun render(delta: Float, state: ScreenState) {
        if (titleDialog.visible) {
            currentTitleDialogDuration += delta
            if (currentTitleDialogDuration >= titleDialogDuration) {
                hideTitle()
            }
        }
        titleDialog.render(delta, state)

        dialog.render(delta, state)
    }

    override fun destroy() {
        titleDialog.destroy()
        dialog.destroy()
    }

    override fun showTitle(text: String, durationMs: Float) {
        if (titleDialog.visible) {
            log.warn("Title dialog already shown. Message '{}' won't shows", text)
            return
        }
        titleDialog.show(text, false)
        titleDialogDuration = durationMs / 1000
        currentTitleDialogDuration = 0f
    }

    override fun hideTitle() {
        hide(titleDialog)
    }

    override fun showDialog(text: String) = dialog.show(text, true)

    override fun hideDialog() = hide(dialog)

    override fun setDialogText(text: String) = dialog.setText(text, true)

    override fun isDialogVisible(): Boolean = dialog.visible

    override fun isDialogTextAnimationCompleted(): Boolean = dialog.isTextAnimationCompleted

    override fun completeDialogTextAnimationImmediately() = dialog.completeTextAnimationImmediately()

    private fun hide(d: DialogComponent) {
        if (d.visible) {
            d.hide()
        }
    }
}