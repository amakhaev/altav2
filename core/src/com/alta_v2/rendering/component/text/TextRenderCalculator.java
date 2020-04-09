package com.alta_v2.rendering.component.text;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import lombok.Getter;

class TextRenderCalculator {

    private final GlyphLayout glyphLayout = new GlyphLayout();
    private final TextStyle style;

    @Getter
    private float x;

    @Getter
    private float y;

    TextRenderCalculator(TextStyle style) {
        this.style = style;
    }

    public void updateCoordinates(String text, BitmapFont font) {
        glyphLayout.setText(font, text);
        x = calculateX(glyphLayout.width);
        y = calculateY(glyphLayout.height);
    }

    private float calculateX(float textWidth) {
        switch (style.getHAlign()) {
            case CENTER:
                return style.getRenderAreaStart().x  + style.getRenderAreaSize().x / 2 - textWidth / 2;
            case RIGHT:
                return style.getRenderAreaStart().x + style.getRenderAreaSize().x - textWidth;
            case LEFT:
            default:
                return style.getRenderAreaStart().x;
        }
    }

    private float calculateY(float textHeight) {
        switch (style.getVAlign()) {
            case TOP:
                return style.getRenderAreaStart().y + style.getRenderAreaSize().y - textHeight;
            case CENTER:
                return style.getRenderAreaStart().y  + style.getRenderAreaSize().y / 2 + textHeight / 2;
            case BOTTOM:
            default:
                return style.getRenderAreaStart().y;
        }
    }
}
