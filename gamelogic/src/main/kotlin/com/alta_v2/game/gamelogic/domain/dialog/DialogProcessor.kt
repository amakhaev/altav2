package com.alta_v2.game.gamelogic.domain.dialog

/**
 * Describes processor that working with dialogs.
 */
interface DialogProcessor {

    /**
     * Sets the text to dialog. Shows it if hidden.
     */
    fun setDialogText(text: String)

    /**
     * Hides the common dialog.
     */
    fun hideDialog()

    /**
     * Hides all dialogs.
     */
    fun hideAllDialogs()

    /**
     * Indicates when text animation of dialog has been completed.
     */
    fun isDialogTextAnimationCompleted(): Boolean

    /**
     * Completes text animation in the dialog immediately.
     */
    fun completeDialogTextAnimationImmediately()

    /**
     * Shows the title message box with given text.
     */
    fun showTitle(text: String)

    /**
     * Hides title message box.
     */
    fun hideTitle();

}