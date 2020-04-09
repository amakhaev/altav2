package com.alta_v2.rendering.common.component.box;

import com.alta_v2.rendering.common.utils.GradientResource;
import com.badlogic.gdx.graphics.Color;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoxStyle {

    private int boxWidth;
    private int boxHeight;
    private int boxX;
    private int boxY;
    private Color boxColor;

    private boolean useBorder;
    private byte borderThickness;
    private Color borderColor;
    private GradientResource.Gradient borderGradient;

}
