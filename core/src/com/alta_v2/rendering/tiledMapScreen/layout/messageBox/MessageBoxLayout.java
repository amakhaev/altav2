package com.alta_v2.rendering.tiledMapScreen.layout.messageBox;

import com.alta_v2.rendering.ScreenState;
import com.alta_v2.rendering.component.ComponentFactory;
import com.alta_v2.rendering.component.dialog.DialogComponent;
import com.alta_v2.rendering.tiledMapScreen.layout.Layout;
import com.google.inject.assistedinject.AssistedInject;

public class MessageBoxLayout implements Layout {

    private final DialogComponent dialogComponent;

    @AssistedInject
    public MessageBoxLayout(ComponentFactory componentFactory) {
        dialogComponent = componentFactory.createDialogComponent();
    }

    @Override
    public void init(ScreenState state) {
        dialogComponent.init(state);
    }

    @Override
    public void render(float delta, ScreenState state) {
        dialogComponent.render(delta, state);
    }

    @Override
    public void destroy() {
        dialogComponent.destroy();
    }

    private void renderText() {
        /*batch.begin();
        ruFont.draw(
                batch,
                message.getText(),
                params.getBoxX() + params.getBoxWidth() / 2 - message.getTextWidth() / 2,
                params.getBoxY() + params.getBorderHeight() / 2 + message.getTextHeight() / 2
        );
        batch.end();*/
    }
}
