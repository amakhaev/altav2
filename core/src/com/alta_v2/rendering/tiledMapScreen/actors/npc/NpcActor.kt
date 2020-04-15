package com.alta_v2.rendering.tiledMapScreen.actors.npc

import com.alta_v2.rendering.tiledMapScreen.actors.PersonActor
import com.alta_v2.rendering.tiledMapScreen.state.NpcState
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch

class NpcActor(texture: Texture, tileWidth: Int, tileHeight: Int) : PersonActor(texture, tileWidth, tileHeight) {

    private var stateTime = 0f

    /**
     * {@inheritDoc}
     */
    fun draw(batch: Batch, delta: Float, npcState: NpcState) {
        stateTime += delta
        val currentFrame = animations[npcState.view]!!.getKeyFrame(stateTime, true)
        batch.draw(currentFrame, npcState.coordinates.x, npcState.coordinates.y)
    }
}