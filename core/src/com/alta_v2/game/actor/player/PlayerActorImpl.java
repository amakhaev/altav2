package com.alta_v2.game.actor.player;

import com.alta_v2.game.utils.AnimationTextureParser;
import com.alta_v2.game.utils.Resources;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Provides the actor that describes the player.
 */
public class PlayerActorImpl extends Actor implements PlayerActor {

    private Animation<TextureRegion> playerSkin;
    private float stateTime;

    @AssistedInject
    public PlayerActorImpl(AssetManager assetManager) {
        Texture texture = assetManager.get(Resources.ACTOR_PERSON_12, Texture.class);
        this.playerSkin = AnimationTextureParser.createPersonAnimation(0.2f, texture, 32, 32);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw (Batch batch, float parentAlpha) {
        this.stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = this.playerSkin.getKeyFrame(this.stateTime, true);
        batch.draw(currentFrame, 0, 0);
    }

}
