package com.alta_v2.rendering.common.component;

import com.alta_v2.rendering.common.component.box.BoxComponent;
import com.alta_v2.rendering.common.component.box.BoxStyle;
import com.alta_v2.rendering.common.component.text.TextComponent;
import com.alta_v2.rendering.common.component.text.TextStyle;
import com.alta_v2.rendering.common.component.dialog.DialogComponent;

public interface ComponentFactory {

    BoxComponent createWindowComponent(BoxStyle configuration);

    TextComponent createTextComponent(TextStyle textStyle);

    DialogComponent createDialogComponent();

}
