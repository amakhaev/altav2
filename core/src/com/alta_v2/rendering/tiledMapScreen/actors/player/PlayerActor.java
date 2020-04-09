package com.alta_v2.rendering.tiledMapScreen.actors.player;

import com.alta_v2.rendering.tiledMapScreen.actors.PersonActor;
import com.alta_v2.rendering.tiledMapScreen.state.PlayerState;
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
    public void draw (Batch batch, float delta, PlayerState playerState) {
        this.stateTime += delta;

        TextureRegion currentFrame;
        if (playerState.isAnimationEnabled() || System.currentTimeMillis() - playerState.getAnimationChangeTime() < 50L) {
            currentFrame = this.animations.get(playerState.getView()).getKeyFrame(this.stateTime, true);
        } else {
            currentFrame = this.animations.get(playerState.getView()).getKeyFrames()[0];
        }

        batch.draw(currentFrame, playerState.getCoordinates().x, playerState.getCoordinates().y);
    }

}
