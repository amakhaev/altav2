package com.alta_v2.rendering.tiledMapScreen.actors.player

import com.alta_v2.rendering.tiledMapScreen.actors.PersonActor
import com.alta_v2.rendering.tiledMapScreen.state.PlayerState
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion

class PlayerActor(texture: Texture, tileWidth: Int, tileHeight: Int) : PersonActor(texture, tileWidth, tileHeight) {
    private var stateTime = 0f

    /**
     * {@inheritDoc}
     */
    fun draw(batch: Batch, delta: Float, playerState: PlayerState) {
        stateTime += delta
        val currentFrame: TextureRegion =
                if (playerState.isAnimationEnabled || System.currentTimeMillis() - playerState.animationChangeTime < 50L) {
                    (animations[playerState.view] ?: error("")).getKeyFrame(stateTime, true)
                } else {
                    (animations[playerState.view] ?: error("")).keyFrames[0]
                }
        batch.draw(currentFrame, playerState.coordinates.x, playerState.coordinates.y)
    }
}