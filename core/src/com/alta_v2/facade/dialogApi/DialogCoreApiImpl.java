package com.alta_v2.facade.dialogApi;

import com.alta_v2.rendering.common.dialog.TitleDialog;
import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class DialogCoreApiImpl implements DialogCoreApi {

    private final TitleDialog titleDialog;

    @Override
    public void showTitleDialog(String message, float durationMs) {
        titleDialog.showTitle(message, durationMs);
    }

    @Override
    public void hideTitleDialog() {
        titleDialog.hideTitle();
    }
}
