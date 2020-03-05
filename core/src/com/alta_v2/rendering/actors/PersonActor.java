package com.alta_v2.rendering.actors;

import com.alta_v2.rendering.utils.AnimationTextureParser;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Map;

/**
 * Provides the person actor high level implementation.
 */
public abstract class PersonActor {

    private final static float ANIMATION_DURATION = 0.15f;

    protected final Map<PersonView, Animation<TextureRegion>> animations;

    /**
     * Initialize new instance of {@link PersonActor}.
     *
     * @param texture - the texture to be used for rendering.
     */
    public PersonActor(Texture texture, int tileWidth, int tileHeight) {
        this.animations = AnimationTextureParser.createPersonAnimation(
                ANIMATION_DURATION, texture, tileWidth, tileHeight
        );
    }
}
