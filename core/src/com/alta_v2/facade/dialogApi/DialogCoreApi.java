package com.alta_v2.facade.dialogApi;

/**
 * Provides API related to dialogs.
 */
public interface DialogCoreApi {

    /**
     * Shows the dialog like title on top of screen.
     *
     * @param message - the text to be shown in the dialog.
     */
    void showTitleDialog(String message, float durationMs);

    /**
     * Hides the title dialog.
     */
    void hideTitleDialog();

}
