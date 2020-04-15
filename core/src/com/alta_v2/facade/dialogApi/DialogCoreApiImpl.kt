package com.alta_v2.facade.dialogApi

import com.alta_v2.rendering.dialog.TitleDialog
import com.google.inject.Inject

class DialogCoreApiImpl @Inject constructor(private val titleDialog: TitleDialog) : DialogCoreApi {

    override fun showTitleDialog(message: String, durationMs: Float) {
        titleDialog.showTitle(message, durationMs)
    }

    override fun hideTitleDialog() {
        titleDialog.hideTitle()
    }
}