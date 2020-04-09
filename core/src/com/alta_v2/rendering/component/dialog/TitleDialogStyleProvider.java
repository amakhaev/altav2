package com.alta_v2.rendering.component.dialog;

import com.alta_v2.rendering.component.ComponentStyle;
import com.alta_v2.rendering.component.animation.AnimationFactory;
import com.alta_v2.rendering.component.animation.FadeAnimation;
import com.alta_v2.rendering.component.animation.TranslationAnimation;
import com.alta_v2.rendering.component.box.BoxStyle;
import com.alta_v2.rendering.component.style.TitleDialogStyle;
import com.alta_v2.rendering.component.text.TextStyle;
import com.alta_v2.rendering.config.AppConfig;
import com.alta_v2.rendering.config.Theme;
import com.alta_v2.rendering.utils.GradientResource;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class TitleDialogStyleProvider {

    private final AppConfig appConfig;
    private final Theme theme;
    private final ComponentStyle componentStyle;
    private final AnimationFactory animationFactory;

    private BoxStyle boxStyle;
    private TextStyle titleStyle;
    private TranslationAnimation boxInAnimation;
    private FadeAnimation textFadeInAnimation;

    public BoxStyle getBoxStyle() {
        if (boxStyle == null) {
            TitleDialogStyle titleDialog = componentStyle.getTitleDialog();

            int boxWidth = appConfig.getWidth() * titleDialog.getBoxWidthPercentage() / 100;
            int boxX = getTitleDialogBoxX(boxWidth);
            int boxY = getTitleDialogBoxY(titleDialog.getBoxHeight());

            boxStyle = BoxStyle.builder()
                    .boxWidth(boxWidth)
                    .boxHeight(titleDialog.getBoxHeight())
                    .boxX(boxX)
                    .boxY(boxY)
                    .boxColor(Color.valueOf(theme.getBox().getColor()))
                    .useBorder(titleDialog.isUseBorder())
                    .borderThickness(titleDialog.getBorderThickness())
                    .borderColor(Color.valueOf(theme.getBox().getBorderColor()))
                    .borderGradient(GradientResource.Gradient.ORANGE)
                    .build();
        }

        return boxStyle;
    }

    public TextStyle getTextStyle() {
        if (titleStyle == null) {
            TitleDialogStyle titleDialog = componentStyle.getTitleDialog();
            BoxStyle boxStyle = getBoxStyle();

            titleStyle = TextStyle.builder()
                    .color(Color.valueOf(theme.getBox().getTextColor()))
                    .hAlign(titleDialog.textHAlign)
                    .vAlign(titleDialog.textVAlign)
                    .renderAreaStart(new Vector2(boxStyle.getBoxX(), boxStyle.getBoxY()))
                    .renderAreaSize(new Vector2(boxStyle.getBoxWidth(), boxStyle.getBoxHeight()))
                    .textSize(componentStyle.getTitleDialog().getTextSize())
                    .build();
        }

        return titleStyle;
    }

    public TranslationAnimation getBoxInAnimation() {
        if (boxInAnimation == null) {
            BoxStyle boxStyle = getBoxStyle();
            boxInAnimation = animationFactory.createFadeInAnimation(
                    new Vector2(boxStyle.getBoxX(), boxStyle.getBoxY() + 50),
                    new Vector2(boxStyle.getBoxX(), boxStyle.getBoxY()),
                    componentStyle.getTitleDialog().getFadeInTime() / 1000
            );
        } else {
            boxInAnimation.reset();
        }

        return boxInAnimation;
    }

    public FadeAnimation getTextFadeInAnimation() {
        if (textFadeInAnimation == null) {
            textFadeInAnimation = animationFactory.creteFadeAnimation(0, 1, componentStyle.getTitleDialog().getFadeInTime() / 1000);
        } else {
            textFadeInAnimation.reset();
        }

        return textFadeInAnimation;
    }

    private int getTitleDialogBoxX(int boxWidth) {
        switch (componentStyle.getTitleDialog().getBoxHAlign()) {
            case LEFT:
                return componentStyle.getTitleDialog().getMarginLeft();
            case CENTER:
                return (appConfig.getWidth() - boxWidth) / 2;
            case RIGHT:
                return appConfig.getWidth() - boxWidth + componentStyle.getTitleDialog().getMarginRight();
            default:
                return 0;
        }
    }

    private int getTitleDialogBoxY(int boxHeight) {
        switch (componentStyle.getTitleDialog().getBoxVAlign()) {
            case TOP:
                return appConfig.getHeight() - boxHeight - componentStyle.getTitleDialog().getMarginTop();
            case CENTER:
                return (appConfig.getHeight() - boxHeight) / 2;
            case BOTTOM:
            default:
                return componentStyle.getTitleDialog().getMarginBottom();
        }
    }

}
