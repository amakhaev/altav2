package com.alta_v2.rendering.common.utils

import com.alta_v2.rendering.tiledMapScreen.actors.PersonView
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.google.common.collect.Maps

fun createPersonAnimation(duration: Float, texture: Texture, tileWidth: Int, tileHeight: Int): Map<PersonView, Animation<TextureRegion>> {

    val tmp = TextureRegion.split(texture, tileWidth, tileHeight)

    return mapOf(
            PersonView.FULL_FACE to Animation(duration, tmp[0][1], tmp[0][0], tmp[0][2]),
            PersonView.SIDE_VIEW_LEFT to Animation(duration, tmp[1][1], tmp[1][0], tmp[1][2]),
            PersonView.SIDE_VIEW_RIGHT to Animation(duration, tmp[2][1], tmp[2][0], tmp[2][2]),
            PersonView.BACK_VIEW to Animation(duration, tmp[3][1], tmp[3][0], tmp[3][2])
    )
}