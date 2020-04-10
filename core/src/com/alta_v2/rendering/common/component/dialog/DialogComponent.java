package com.alta_v2.rendering.common.component.dialog;

import com.alta_v2.rendering.Renderer;
import com.alta_v2.rendering.ScreenState;
import com.alta_v2.rendering.common.component.ComponentFactory;
import com.alta_v2.rendering.common.component.animation.FadeAnimation;
import com.alta_v2.rendering.common.component.animation.TranslationAnimation;
import com.alta_v2.rendering.common.component.box.BoxComponent;
import com.alta_v2.rendering.common.component.box.BoxStyle;
import com.alta_v2.rendering.common.component.text.TextComponent;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import lombok.Getter;

public class DialogComponent implements Renderer {

    private final BoxComponent boxComponent;
    private final TextComponent textComponent;
    private final TranslationAnimation boxFadeIn;
    private final TranslationAnimation boxFadeOut;
    private final FadeAnimation textFadeIn;

    private TranslationAnimation currentBoxAnimation;
    private FadeAnimation currentTextFade;

    private String text;

    @Getter
    private boolean visible;

    @AssistedInject
    public DialogComponent(@Assisted("fadeIn") TranslationAnimation fadeIn,
                           @Assisted("fadeOut") TranslationAnimation fadeOut,
                           TitleDialogStyleProvider styleProvider,
                           ComponentFactory componentFactory) {
        BoxStyle boxStyle = styleProvider.createBoxStyle();
        boxComponent = componentFactory.createBoxComponent(boxStyle);
        textComponent = componentFactory.createTextComponent(styleProvider.createTextStyle());

        textFadeIn = styleProvider.createTextFadeInAnimation();

        boxFadeOut = fadeOut;
        boxFadeIn = fadeIn;
        boxFadeIn.setCompleteListener(this::onBoxFadeInComplete);
    }

    @Override
    public void init(ScreenState state) {
        boxComponent.init(state);
        textComponent.init(state);
    }

    @Override
    public void render(float delta, ScreenState state) {
        if (currentBoxAnimation != null) {
            currentBoxAnimation.act(delta);
            boxComponent.setPosition(currentBoxAnimation.getX(), currentBoxAnimation.getY());
            boxComponent.render(delta, state);
        }

        if (currentTextFade != null) {
            currentTextFade.act(delta);
            textComponent.setAlpha(currentTextFade.getAlpha());
            textComponent.render(delta, state);
        }
    }

    @Override
    public void destroy() {
        boxComponent.destroy();
        textComponent.destroy();

        boxFadeIn.destroy();
        boxFadeOut.destroy();
        textFadeIn.destroy();

        currentBoxAnimation = null;
        currentTextFade = null;
    }

    public void show(String message) {
        // runs box animation
        currentBoxAnimation = boxFadeIn;
        currentBoxAnimation.reset();

        textComponent.setText("");
        text = message;
        visible = true;
    }

    public void hide() {
        textComponent.setText("");
        currentBoxAnimation = boxFadeOut;
        currentBoxAnimation.reset();
        visible = false;
    }

    private void onBoxFadeInComplete() {
        textComponent.setText(text);

        // runs text animation
        currentTextFade = textFadeIn;
        currentTextFade.reset();
    }
}
