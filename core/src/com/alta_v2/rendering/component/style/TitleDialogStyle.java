package com.alta_v2.rendering.component.style;

import lombok.Data;

@Data
public class TitleDialogStyle {
    private float fadeInTime;
    private float fadeOutTime;

    private int boxWidthPercentage;
    private int boxHeight;
    public HorizontalAlign boxHAlign;
    public VerticalAlign boxVAlign;
    private int marginTop;
    private int marginBottom;
    private int marginRight;
    private int marginLeft;

    private boolean useBorder;
    private byte borderThickness;

    public HorizontalAlign textHAlign;
    public VerticalAlign textVAlign;
    private int textSize;
}
