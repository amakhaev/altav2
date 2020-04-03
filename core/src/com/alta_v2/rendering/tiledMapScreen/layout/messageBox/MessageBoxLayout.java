package com.alta_v2.rendering.tiledMapScreen.layout.messageBox;

import com.alta_v2.game.utils.Resources;
import com.alta_v2.rendering.ScreenState;
import com.alta_v2.rendering.component.ComponentFactory;
import com.alta_v2.rendering.component.box.BoxComponent;
import com.alta_v2.rendering.component.box.BoxStyle;
import com.alta_v2.rendering.component.box.BoxStyleProvider;
import com.alta_v2.rendering.tiledMapScreen.layout.Layout;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

public class MessageBoxLayout implements Layout {

    private final Message message = new Message();
    private final BoxComponent boxComponent;

    private BitmapFont ruFont;
    private SpriteBatch batch;

    @AssistedInject
    public MessageBoxLayout(@Assisted BoxStyle style,
                            ComponentFactory componentFactory,
                            BoxStyleProvider styleProvider) {
        boxComponent = componentFactory.createWindowComponent(style);
    }

    @Override
    public void init(ScreenState state) {
        boxComponent.init(state);
        batch = new SpriteBatch();
        ruFont = generateFont();
        message.setText("Мой супер текст", ruFont);
    }

    @Override
    public void render(float delta, ScreenState state) {
        boxComponent.render(delta, state);
//        renderText();
    }

    @Override
    public void destroy() {
        boxComponent.destroy();
        ruFont.dispose();
    }

    private void renderText() {
        /*batch.begin();
        ruFont.draw(
                batch,
                message.getText(),
                params.getBoxX() + params.getBoxWidth() / 2 - message.getTextWidth() / 2,
                params.getBoxY() + params.getBorderHeight() / 2 + message.getTextHeight() / 2
        );
        batch.end();*/
    }

    private BitmapFont generateFont() {

        // Configure font parameters
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = Resources.RUSSIAN_CHARACTERS;
        // parameter.size = params.getTextSize();

        // Generate font
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator( Gdx.files.internal(Resources.RU_FONT_NAME) );
        BitmapFont font = generator.generateFont(parameter);

        // Dispose resources
        generator.dispose();

        return font;
    }
}
