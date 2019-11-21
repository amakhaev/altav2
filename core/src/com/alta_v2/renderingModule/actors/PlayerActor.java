package com.alta_v2.renderingModule.actors;

import com.alta_v2.renderingModule.utils.AnimationTextureParser;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PlayerActor extends Actor {

    private Animation<TextureRegion> playerSkin;
    private float stateTime;


    /**
     * Initialize new instance of {@link PlayerActor}.
     *
     * @param texture - the texture to be used for rendering.
     */
    public PlayerActor(Texture texture) {
        this.playerSkin = AnimationTextureParser.createPersonAnimation(0.2f, texture, 32, 32);
    }

    /**
     * {@inheritDoc}
     */
    public void draw (Batch batch, float delta, float x, float y) {
        this.stateTime += delta;
        TextureRegion currentFrame = this.playerSkin.getKeyFrame(this.stateTime, true);
        batch.draw(currentFrame, x, y);
    }

}
