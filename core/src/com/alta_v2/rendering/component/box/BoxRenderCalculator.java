package com.alta_v2.rendering.component.box;

import lombok.Getter;

class BoxRenderCalculator {

    private final BoxStyle configuration;

    @Getter
    private int boxWidth;

    @Getter
    private int boxHeight;

    @Getter
    private float boxX;

    @Getter
    private float boxY;

    @Getter
    private float borderX;

    @Getter
    private float borderY;

    @Getter
    private int borderWidth;

    @Getter
    private int borderHeight;

    @Getter
    private byte borderThickness = 1;

    BoxRenderCalculator(BoxStyle configuration) {
        this.configuration = configuration;
        boxX = configuration.getBoxX();
        boxY = configuration.getBoxY();
        boxWidth = configuration.getBoxWidth();
        boxHeight = configuration.getBoxHeight();
        borderThickness = configuration.getBorderThickness();
        calculateBorder();
    }

    public void updatePosition(float x, float y) {
        boxX = x;
        boxY = y;
        calculateBorder();
    }

    private void calculateBorder() {
        borderWidth = boxWidth + borderThickness;
        borderHeight = boxHeight + borderThickness;
        borderX = boxX - 1;
        borderY = boxY - 1;
    }
}
