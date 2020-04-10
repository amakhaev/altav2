package com.alta_v2.rendering.common.dialog;

import com.alta_v2.rendering.Renderer;
import com.alta_v2.rendering.ScreenState;
import com.alta_v2.rendering.common.component.ComponentFactory;
import com.alta_v2.rendering.common.component.dialog.DialogComponent;
import com.alta_v2.rendering.common.component.dialog.TitleDialogStyleProvider;
import com.google.inject.Inject;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class DialogImpl implements Renderer, TitleDialog {

    private final DialogComponent titleDialog;

    private float titleDialogDuration;
    private float currentTitleDialogDuration;

    @Inject
    public DialogImpl(ComponentFactory componentFactory, TitleDialogStyleProvider styleProvider) {
        titleDialog = componentFactory.createDialogComponent(
                styleProvider.createBoxInAnimation(), styleProvider.createBoxOutAnimation()
        );
    }

    @Override
    public void init(ScreenState state) {
        titleDialog.init(state);
    }

    @Override
    public void render(float delta, ScreenState state) {
        if (titleDialog.isVisible()) {
            currentTitleDialogDuration += delta;

            if (currentTitleDialogDuration >= titleDialogDuration) {
                hideTitle();
            }
        }

        titleDialog.render(delta, state);
    }

    @Override
    public void destroy() {
        titleDialog.destroy();
    }

    @Override
    public void showTitle(String text, float durationMs) {
        if (titleDialog.isVisible()) {
            log.warn("Title dialog already shown. Message '{}' won't shows", text);
            return;
        }
        titleDialog.show(text);
        titleDialogDuration = durationMs / 1000;
        currentTitleDialogDuration = 0;
    }

    @Override
    public void hideTitle() {
        if (titleDialog.isVisible()) {
            titleDialog.hide();
        }
    }
}
