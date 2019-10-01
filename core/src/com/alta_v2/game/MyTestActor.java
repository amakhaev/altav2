package com.alta_v2.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MyTestActor extends Actor {

    private Texture texture;

    public MyTestActor() {
        this.texture = new Texture(Gdx.files.internal("badlogic.jpg"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw (Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(this.texture, 0, 0);
    }
}
