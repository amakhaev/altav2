package com.alta_v2.rendering.component.box;

import lombok.Getter;

class RendererCalculator {

    private final BoxStyle configuration;

    @Getter
    private int boxWidth;

    @Getter
    private int boxHeight;

    @Getter
    private int boxX;

    @Getter
    private int boxY;

    @Getter
    private int borderX;

    @Getter
    private int borderY;

    @Getter
    private int borderWidth;

    @Getter
    private int borderHeight;

    @Getter
    private byte borderThickness = 1;

    RendererCalculator(BoxStyle configuration) {
        this.configuration = configuration;
        boxX = configuration.getBoxX();
        boxY = configuration.getBoxY();
        boxWidth = configuration.getBoxWidth();
        boxHeight = configuration.getBoxHeight();
        borderThickness = configuration.getBorderThickness();
        calculateBorder();
    }

    private void calculateBorder() {
        borderWidth = boxWidth + borderThickness * 2;
        borderHeight = boxHeight + borderThickness * 2;
        borderX = boxX - borderThickness;
        borderY = boxY - borderThickness;
    }
}
