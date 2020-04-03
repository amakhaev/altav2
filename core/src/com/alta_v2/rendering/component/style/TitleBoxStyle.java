package com.alta_v2.rendering.component.style;

import lombok.Data;

@Data
public class TitleBoxStyle {
    private int boxWidthPercentage;
    private int boxHeight;
    public HorizontalAlign hAlign;
    public VerticalAlign vAlign;
    private int marginTop;
    private int marginBottom;
    private int marginRight;
    private int marginLeft;

    private boolean useBorder;
    private byte borderThickness;

}
