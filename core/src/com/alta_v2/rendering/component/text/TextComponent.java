package com.alta_v2.rendering.component.text;

import com.alta_v2.game.utils.Resources;
import com.alta_v2.rendering.Renderer;
import com.alta_v2.rendering.ScreenState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

public class TextComponent implements Renderer {

    private final TextRenderCalculator calculator;
    private final TextStyle textStyle;

    @AssistedInject
    public TextComponent(@Assisted TextStyle textStyle) {
        this.textStyle = textStyle;
        calculator = new TextRenderCalculator(textStyle);
    }

    private BitmapFont ruFont;
    private SpriteBatch batch;
    private String currentText = "";

    @Override
    public void init(ScreenState state) {
        batch = new SpriteBatch();
        ruFont = generateFont();
    }

    @Override
    public void render(float delta, ScreenState state) {
        batch.begin();
        ruFont.draw(batch, currentText, calculator.getX(), calculator.getY());
        batch.end();
    }

    @Override
    public void destroy() {
        batch.dispose();
        ruFont.dispose();
    }

    public void setText(String currentText) {
        this.currentText = currentText;
        calculator.updateCoordinates(currentText, ruFont);
    }

    public void setAlpha(float alpha) {
        if (ruFont.getColor().a != alpha) {
            ruFont.getColor().a = alpha;
        }
    }

    private BitmapFont generateFont() {

        // Configure font parameters
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = Resources.RUSSIAN_CHARACTERS;
        parameter.size = textStyle.getTextSize();
        parameter.color = textStyle.getColor();

        // Generate font
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator( Gdx.files.internal(Resources.RU_FONT_NAME) );
        BitmapFont font = generator.generateFont(parameter);

        // Dispose resources
        generator.dispose();

        return font;
    }
}
