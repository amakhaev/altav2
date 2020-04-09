package com.alta_v2.rendering.common.component.text;

import com.alta_v2.rendering.common.component.style.HorizontalAlign;
import com.alta_v2.rendering.common.component.style.VerticalAlign;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TextStyle {

    private Color color;
    private Vector2 renderAreaStart;
    private Vector2 renderAreaSize;
    private HorizontalAlign hAlign;
    private VerticalAlign vAlign;
    private int textSize;

}
