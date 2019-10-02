package com.alta_v2.game.actor;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.google.inject.assistedinject.AssistedInject;

public class SecondTestActor extends Actor {

    private Texture texture;

    @AssistedInject
    public SecondTestActor(AssetManager assetManager) {
        this.texture = assetManager.get("test.png", Texture.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw (Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(this.texture, 250, 200);
    }
}
