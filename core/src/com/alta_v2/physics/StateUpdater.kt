package com.alta_v2.physics

import com.alta_v2.physics.executionContext.TiledMapEngineContext
import com.alta_v2.rendering.tiledMapScreen.TiledMapState
import com.alta_v2.rendering.tiledMapScreen.state.NpcState
import com.alta_v2.rendering.tiledMapScreen.state.PlayerState

/**
 * Updates given state for new values from context.
 */
class StateUpdater {

    companion object {
        fun updateAll(state: TiledMapState, context: TiledMapEngineContext) {
            updateTiledMapState(state, context)
            updatePlayerState(state.player, context)
            for (npcState in state.npcStateList) {
                updateNpcState(npcState, context)
            }
        }

        private fun updateTiledMapState(state: TiledMapState, context: TiledMapEngineContext) {
            state.updateMapCoordinates(context.focusPointGlobal.x, context.focusPointGlobal.y)
        }

        private fun updatePlayerState(playerState: PlayerState, context: TiledMapEngineContext) {
            playerState.updateCoordinates(
                    context.player.globalPoint.x, context.player.globalPoint.y
            )
            playerState.view = context.player.view.getValue()
            playerState.isAnimationEnabled = context.player.isMoving.getValue()
            playerState.animationChangeTime = context.player.isMoving.getChangeTime()
        }

        private fun updateNpcState(npcState: NpcState, context: TiledMapEngineContext) {
            if (!context.npcMap.containsKey(npcState.id)) {
                return
            }
            npcState.coordinates.x = context.npcMap[npcState.id]!!.globalPoint.x
            npcState.coordinates.y = context.npcMap[npcState.id]!!.globalPoint.y
            npcState.view = context.npcMap[npcState.id]!!.view.getValue()
        }
    }
}