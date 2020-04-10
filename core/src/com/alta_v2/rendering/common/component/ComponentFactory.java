package com.alta_v2.rendering.common.component;

import com.alta_v2.rendering.common.component.animation.TranslationAnimation;
import com.alta_v2.rendering.common.component.box.BoxComponent;
import com.alta_v2.rendering.common.component.box.BoxStyle;
import com.alta_v2.rendering.common.component.dialog.DialogComponent;
import com.alta_v2.rendering.common.component.text.TextComponent;
import com.alta_v2.rendering.common.component.text.TextStyle;
import com.google.inject.assistedinject.Assisted;

public interface ComponentFactory {

    BoxComponent createBoxComponent(BoxStyle configuration);

    TextComponent createTextComponent(TextStyle textStyle);

    DialogComponent createDialogComponent(@Assisted("fadeIn") TranslationAnimation fadeIn,
                                          @Assisted("fadeOut") TranslationAnimation fadeOut);

}

