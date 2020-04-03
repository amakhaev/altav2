package com.alta_v2.rendering.component;

import com.alta_v2.rendering.component.box.BoxComponent;
import com.alta_v2.rendering.component.box.BoxStyle;

public interface ComponentFactory {

    BoxComponent createWindowComponent(BoxStyle configuration);

}
