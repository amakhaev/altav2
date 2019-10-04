package com.alta_v2.game.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import lombok.experimental.UtilityClass;

/**
 * Provides the parser of textures to create animation from it.
 */
@UtilityClass
public class AnimationTextureParser {

    public Animation<TextureRegion> createPersonAnimation(float duration, Texture texture, int tileWidth, int tileHeight) {
        TextureRegion[][] tmp = TextureRegion.split(texture, tileWidth, tileHeight);
        return new Animation<>(duration, tmp[0][1], tmp[0][0], tmp[0][2]);
    }

}
