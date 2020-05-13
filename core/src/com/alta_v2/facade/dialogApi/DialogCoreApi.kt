package com.alta_v2.facade.dialogApi

/**
 * Provides API related to dialogs.
 */
interface DialogCoreApi {
    /**
     * Shows the dialog like title on top of screen.
     *
     * @param message - the text to be shown in the dialog.
     */
    fun showTitleDialog(message: String, durationMs: Float)

    /**
     * Hides the title dialog.
     */
    fun hideTitleDialog()

    /**
     * Shows the common dialog with given text.
     */
    fun showDialog(text: String)

    /**
     * Hides the common dialog.
     */
    fun hideDialog()
}