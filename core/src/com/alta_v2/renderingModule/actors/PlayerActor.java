package com.alta_v2.renderingModule.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayerActor extends PersonActor {

    private float stateTime;


    /**
     * Initialize new instance of {@link PlayerActor}.
     *
     * @param texture - the texture to be used for rendering.
     */
    public PlayerActor(Texture texture, int tileWidth, int tileHeight) {
        super(texture, tileWidth, tileHeight);
    }

    /**
     * {@inheritDoc}
     */
    public void draw (Batch batch, float delta, float x, float y, PersonView view, boolean isAnimated) {
        this.stateTime += delta;

        TextureRegion currentFrame;
        if (isAnimated) {
            currentFrame = this.animations.get(view).getKeyFrame(this.stateTime, true);
        } else {
            currentFrame = this.animations.get(view).getKeyFrames()[0];
        }

        batch.draw(currentFrame, x, y);
    }

}
