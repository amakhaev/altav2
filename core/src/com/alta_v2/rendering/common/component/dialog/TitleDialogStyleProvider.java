package com.alta_v2.rendering.common.component.dialog;

import com.alta_v2.rendering.common.component.ComponentStyle;
import com.alta_v2.rendering.common.component.animation.AnimationFactory;
import com.alta_v2.rendering.common.component.animation.FadeAnimation;
import com.alta_v2.rendering.common.component.animation.TranslationAnimation;
import com.alta_v2.rendering.common.component.box.BoxStyle;
import com.alta_v2.rendering.common.component.style.TitleDialogStyle;
import com.alta_v2.rendering.common.component.text.TextStyle;
import com.alta_v2.rendering.common.utils.GradientResource;
import com.alta_v2.rendering.config.AppConfig;
import com.alta_v2.rendering.config.Theme;
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

    public BoxStyle createBoxStyle() {
        TitleDialogStyle titleDialog = componentStyle.getTitleDialog();

        int boxWidth = appConfig.getWidth() * titleDialog.getBoxWidthPercentage() / 100;
        int boxX = getTitleDialogBoxX(boxWidth);
        int boxY = getTitleDialogBoxY(titleDialog.getBoxHeight());

        return BoxStyle.builder()
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

    public TextStyle createTextStyle() {
        TitleDialogStyle titleDialog = componentStyle.getTitleDialog();
        BoxStyle boxStyle = createBoxStyle();

        return TextStyle.builder()
                .color(Color.valueOf(theme.getBox().getTextColor()))
                .hAlign(titleDialog.textHAlign)
                .vAlign(titleDialog.textVAlign)
                .renderAreaStart(new Vector2(boxStyle.getBoxX(), boxStyle.getBoxY()))
                .renderAreaSize(new Vector2(boxStyle.getBoxWidth(), boxStyle.getBoxHeight()))
                .textSize(componentStyle.getTitleDialog().getTextSize())
                .build();
    }

    public TranslationAnimation createBoxInAnimation() {
        BoxStyle boxStyle = createBoxStyle();
        return animationFactory.createFadeInAnimation(
                new Vector2(boxStyle.getBoxX(), boxStyle.getBoxY() + 50),
                new Vector2(boxStyle.getBoxX(), boxStyle.getBoxY()),
                componentStyle.getTitleDialog().getFadeInTime() / 1000
        );
    }

    public FadeAnimation createTextFadeInAnimation() {
        return animationFactory.creteFadeAnimation(0, 1, componentStyle.getTitleDialog().getFadeInTime() / 1000);
    }

    public TranslationAnimation createBoxOutAnimation() {
        BoxStyle boxStyle = createBoxStyle();
        return animationFactory.createFadeInAnimation(
                new Vector2(boxStyle.getBoxX(), boxStyle.getBoxY()),
                new Vector2(boxStyle.getBoxX(), boxStyle.getBoxY() + 50),
                componentStyle.getTitleDialog().getFadeInTime() / 1000
        );
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
