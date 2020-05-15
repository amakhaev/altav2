package com.alta_v2.facade.dialogApi

import com.alta_v2.rendering.dialog.Dialog
import com.google.inject.Inject

class DialogCoreApiImpl @Inject constructor(private val dialog: Dialog) : DialogCoreApi {

    override fun showTitleDialog(message: String, durationMs: Float) = dialog.showTitle(message, durationMs)

    override fun hideTitleDialog() = dialog.hideTitle()

    override fun showDialog(text: String) = dialog.showDialog(text)

    override fun hideDialog() = dialog.hideDialog()

    override fun isDialogTextAnimationCompleted(): Boolean = dialog.isDialogTextAnimationCompleted()

    override fun completeDialogTextAnimationImmediately() = dialog.completeDialogTextAnimationImmediately()

    override fun setDialogText(text: String) = dialog.setDialogText(text)

    override fun isDialogVisible(): Boolean = dialog.isDialogVisible()
}