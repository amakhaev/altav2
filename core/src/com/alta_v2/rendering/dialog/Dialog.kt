package com.alta_v2.rendering.dialog

interface Dialog {
    fun showTitle(text: String, durationMs: Float)
    fun hideTitle()

    fun showDialog(text: String)
    fun hideDialog()
    fun setDialogText(text: String)
    fun isDialogVisible(): Boolean
    fun isDialogTextAnimationCompleted(): Boolean
    fun completeDialogTextAnimationImmediately()
}