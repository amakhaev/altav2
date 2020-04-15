package com.alta_v2.rendering.tiledMapScreen.actors

import com.alta_v2.rendering.common.utils.createPersonAnimation
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion

/**
 * Provides the person actor high level implementation.
 */
abstract class PersonActor(texture: Texture, tileWidth: Int, tileHeight: Int) {

    protected val animations: Map<PersonView, Animation<TextureRegion>> = createPersonAnimation(
            ANIMATION_DURATION, texture, tileWidth, tileHeight
    )

    companion object {
        private const val ANIMATION_DURATION = 0.15f
    }
}