package com.alta_v2.renderingModule.utils;

import com.alta_v2.renderingModule.actors.PersonView;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.google.common.collect.Maps;
import lombok.experimental.UtilityClass;

import java.util.Map;

/**
 * Provides the parser of textures to create animation from it.
 */
@UtilityClass
public class AnimationTextureParser {

    public Map<PersonView, Animation<TextureRegion>> createPersonAnimation(
            float duration, Texture texture, int tileWidth, int tileHeight) {

        TextureRegion[][] tmp = TextureRegion.split(texture, tileWidth, tileHeight);
        Map<PersonView, Animation<TextureRegion>> animations = Maps.newHashMap();
        animations.put(PersonView.FULL_FACE, new Animation<>(duration, tmp[0][1], tmp[0][0], tmp[0][2]));
        animations.put(PersonView.SIDE_VIEW_LEFT, new Animation<>(duration, tmp[1][1], tmp[1][0], tmp[1][2]));
        animations.put(PersonView.SIDE_VIEW_RIGHT, new Animation<>(duration, tmp[2][1], tmp[2][0], tmp[2][2]));
        animations.put(PersonView.BACK_VIEW, new Animation<>(duration, tmp[3][1], tmp[3][0], tmp[3][2]));

        return animations;
    }
}
