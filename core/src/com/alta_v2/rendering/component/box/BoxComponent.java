package com.alta_v2.rendering.component.box;

import com.alta_v2.rendering.Renderer;
import com.alta_v2.rendering.ScreenState;
import com.alta_v2.rendering.utils.GradientResource;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import java.util.List;

public class BoxComponent implements Renderer {

    private final BoxRenderCalculator calculator;
    private final BoxStyle style;

    private ShapeRenderer boxRenderer;
    private Rectangle box;

    private ShapeRenderer borderRenderer;
    private Rectangle border;

    @AssistedInject
    public BoxComponent(@Assisted BoxStyle style) {
        calculator = new BoxRenderCalculator(style);
        this.style = style;
    }

    @Override
    public void init(ScreenState state) {
        boxRenderer = new ShapeRenderer();
        boxRenderer.setAutoShapeType(true);
        boxRenderer.setColor(style.getBoxColor());
        box = new Rectangle();

        borderRenderer = new ShapeRenderer();
        borderRenderer.setAutoShapeType(true);
        borderRenderer.setColor(style.getBorderColor());
        border = new Rectangle();
    }

    @Override
    public void render(float delta, ScreenState state) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        boxRenderer.begin(ShapeRenderer.ShapeType.Filled);
        boxRenderer.rect(calculator.getBoxX(), calculator.getBoxY(), calculator.getBoxWidth(), calculator.getBoxHeight());
        boxRenderer.end();

        if (!style.isUseBorder()) {
            return;
        }

        List<Color> borderGradient = GradientResource.gradients.get(style.getBorderGradient());
        Gdx.gl.glLineWidth(calculator.getBorderThickness());
        borderRenderer.begin(ShapeRenderer.ShapeType.Point);
        if (borderGradient != null) {
            borderRenderer.rect(
                    calculator.getBorderX(), calculator.getBorderY(),
                    calculator.getBorderWidth(), calculator.getBorderHeight(),
                    borderGradient.get(0), borderGradient.get(1), borderGradient.get(2), borderGradient.get(3)
            );
        } else {
            borderRenderer.rect(
                    calculator.getBorderX(), calculator.getBorderY(),
                    calculator.getBorderWidth(), calculator.getBorderHeight()
            );
        }
        borderRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    @Override
    public void destroy() {
        boxRenderer.dispose();
        borderRenderer.dispose();
    }

    public void setPosition(float x, float y) {
        calculator.updatePosition(x, y);
    }
}
