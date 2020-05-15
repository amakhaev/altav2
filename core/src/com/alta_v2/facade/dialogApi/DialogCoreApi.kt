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

    /**
     * Indicates when text animation of dialog has been completed.
     */
    fun isDialogTextAnimationCompleted(): Boolean

    /**
     * Completes text animation in the dialog immediately.
     */
    fun completeDialogTextAnimationImmediately()

    /**
     * Sets the current text of dialog.
     */
    fun setDialogText(text: String)

    /**
     * Indicates if dialog has been shown or no.
     */
    fun isDialogVisible(): Boolean
}