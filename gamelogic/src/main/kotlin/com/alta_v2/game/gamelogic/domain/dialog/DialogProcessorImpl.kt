package com.alta_v2.game.gamelogic.domain.dialog

import com.alta_v2.facade.dialogApi.DialogCoreApi
import com.google.inject.Inject

class DialogProcessorImpl @Inject constructor(private val dialogCoreApi: DialogCoreApi) : DialogProcessor {

    override fun showTitle(text: String) = dialogCoreApi.showTitleDialog(text, 3000f)

    override fun hideTitle() = dialogCoreApi.hideTitleDialog()

    override fun setDialogText(text: String) =
            if (dialogCoreApi.isDialogVisible()) {
                dialogCoreApi.setDialogText(text)
            } else {
                dialogCoreApi.showDialog(text)
            }

    override fun hideDialog() = dialogCoreApi.hideDialog()

    override fun hideAllDialogs() {
        dialogCoreApi.hideTitleDialog()
        dialogCoreApi.hideDialog()
    }

    override fun isDialogTextAnimationCompleted(): Boolean = dialogCoreApi.isDialogTextAnimationCompleted()

    override fun completeDialogTextAnimationImmediately() = dialogCoreApi.completeDialogTextAnimationImmediately()

}