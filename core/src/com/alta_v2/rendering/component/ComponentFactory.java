package com.alta_v2.rendering.component;

import com.alta_v2.rendering.component.box.BoxComponent;
import com.alta_v2.rendering.component.box.BoxStyle;
import com.alta_v2.rendering.component.text.TextComponent;
import com.alta_v2.rendering.component.text.TextStyle;
import com.alta_v2.rendering.component.dialog.DialogComponent;

public interface ComponentFactory {

    BoxComponent createWindowComponent(BoxStyle configuration);

    TextComponent createTextComponent(TextStyle textStyle);

    DialogComponent createDialogComponent();

}
