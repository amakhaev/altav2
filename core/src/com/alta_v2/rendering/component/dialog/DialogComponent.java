package com.alta_v2.rendering.component.dialog;

import com.alta_v2.rendering.Renderer;
import com.alta_v2.rendering.ScreenState;
import com.alta_v2.rendering.component.ComponentFactory;
import com.alta_v2.rendering.component.animation.FadeAnimation;
import com.alta_v2.rendering.component.animation.TranslationAnimation;
import com.alta_v2.rendering.component.box.BoxComponent;
import com.alta_v2.rendering.component.box.BoxStyle;
import com.alta_v2.rendering.component.text.TextComponent;
import com.google.inject.assistedinject.AssistedInject;

public class DialogComponent implements Renderer {

    private final BoxComponent boxComponent;
    private final TextComponent textComponent;
    private final TranslationAnimation boxFadeIn;
    private final FadeAnimation textFadeIn;

    private TranslationAnimation currentBoxAnimation;
    private FadeAnimation currentTextFade;

    private String text = "мой супер длинный длинный длинный длинный текст";

    @AssistedInject
    public DialogComponent(TitleDialogStyleProvider styleProvider, ComponentFactory componentFactory) {
        BoxStyle boxStyle = styleProvider.getBoxStyle();
        boxComponent = componentFactory.createWindowComponent(boxStyle);
        textComponent = componentFactory.createTextComponent(styleProvider.getTextStyle());

        textFadeIn = styleProvider.getTextFadeInAnimation();
        boxFadeIn = styleProvider.getBoxInAnimation();
        boxFadeIn.setCompleteListener(this::onBoxFadeInComplete);
    }

    @Override
    public void init(ScreenState state) {
        boxComponent.init(state);
        textComponent.init(state);
        show();
    }

    @Override
    public void render(float delta, ScreenState state) {
        if (currentBoxAnimation != null) {
            currentBoxAnimation.act(delta);
            boxComponent.setPosition(currentBoxAnimation.getX(), currentBoxAnimation.getY());
        }

        if (currentTextFade != null) {
            currentTextFade.act(delta);
            textComponent.setAlpha(currentTextFade.getAlpha());
        }

        boxComponent.render(delta, state);
        textComponent.render(delta, state);
    }

    @Override
    public void destroy() {
        boxComponent.destroy();
        textComponent.destroy();

        boxFadeIn.destroy();
        boxFadeIn.setCompleteListener(null);

        textFadeIn.destroy();

        currentBoxAnimation = null;
        currentTextFade = null;
    }

    public void show() {
        // runs box animation
        currentBoxAnimation = boxFadeIn;
        currentBoxAnimation.reset();
    }

    private void onBoxFadeInComplete() {
        textComponent.setText(text);

        // runs text animation
        currentTextFade = textFadeIn;
        currentTextFade.reset();
    }
}
