package com.alta_v2.rendering.component.box;

import com.alta_v2.rendering.component.ComponentStyle;
import com.alta_v2.rendering.component.style.HorizontalAlign;
import com.alta_v2.rendering.component.style.TitleBoxStyle;
import com.alta_v2.rendering.component.style.VerticalAlign;
import com.alta_v2.rendering.config.AppConfig;
import com.alta_v2.rendering.config.Theme;
import com.alta_v2.rendering.utils.GradientResource;
import com.badlogic.gdx.graphics.Color;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class BoxStyleProvider {

    private final AppConfig appConfig;
    private final Theme theme;
    private final ComponentStyle componentStyle;

    public BoxStyle getTitleBox() {
        TitleBoxStyle titleBox = componentStyle.getTitleBox();

        int boxWidth = appConfig.getWidth() * titleBox.getBoxWidthPercentage() / 100;

        return BoxStyle.builder()
                .boxWidth(boxWidth)
                .boxHeight(titleBox.getBoxHeight())
                .boxX(getTitleBoxX(boxWidth))
                .boxY(getTitleBoxY(titleBox.getBoxHeight()))
                .boxColor(new Color(theme.getBox().getColor()))
                .useBorder(titleBox.isUseBorder())
                .borderThickness(titleBox.getBorderThickness())
                .borderColor(new Color(theme.getBox().getBorderColor()))
                .borderGradient(GradientResource.Gradient.ORANGE)
                .build();
    }

    private int getTitleBoxX(int boxWidth) {
        switch (componentStyle.getTitleBox().getHAlign()) {
            case LEFT:
                return componentStyle.getTitleBox().getMarginLeft();
            case CENTER:
                return (appConfig.getWidth() - boxWidth) / 2;
            case RIGHT:
                return appConfig.getWidth() - boxWidth + componentStyle.getTitleBox().getMarginRight();
            default:
                return 0;
        }
    }

    private int getTitleBoxY(int boxHeight) {
        switch (componentStyle.getTitleBox().getVAlign()) {
            case TOP:
                return appConfig.getHeight() - boxHeight - componentStyle.getTitleBox().getMarginTop();
            case CENTER:
                return (appConfig.getHeight() - boxHeight) / 2;
            case BOTTOM:
            default:
                return componentStyle.getTitleBox().getMarginBottom();
        }
    }

}
