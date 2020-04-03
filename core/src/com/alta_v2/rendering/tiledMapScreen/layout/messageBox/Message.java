package com.alta_v2.rendering.tiledMapScreen.layout.messageBox;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.google.common.base.Strings;
import lombok.Getter;

class Message {

    private final GlyphLayout glyphLayout = new GlyphLayout();

    @Getter
    private String text;

    @Getter
    private float textWidth;

    @Getter
    private float textHeight;

    public void setText(String text, BitmapFont font) {
        glyphLayout.reset();
        if (Strings.isNullOrEmpty(text)) {
            this.text = null;
            textWidth = 0;
            textHeight = 0;
        } else {
            this.text = text;
            glyphLayout.setText(font, text);
            textWidth = glyphLayout.width;
            textHeight = glyphLayout.height;
        }
    }
}
