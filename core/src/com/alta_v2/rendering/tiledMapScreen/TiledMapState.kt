package com.alta_v2.rendering.tiledMapScreen

import com.alta_v2.rendering.ScreenState
import com.alta_v2.rendering.tiledMapScreen.state.NpcState
import com.alta_v2.rendering.tiledMapScreen.state.PlayerState
import com.badlogic.gdx.math.Vector2
import com.google.inject.assistedinject.Assisted
import com.google.inject.assistedinject.AssistedInject
import java.util.*
import java.util.function.Consumer

/**
 * Provides the current state of tiled map.
 */
class TiledMapState @AssistedInject constructor(@Assisted npcIds: List<Int>) : ScreenState {


    val mapCoordinates = Vector2()
    val player = PlayerState()
    val npcStateList: MutableList<NpcState> = ArrayList()

    init {
        npcIds.forEach(Consumer { npcId: Int -> npcStateList.add(NpcState(npcId)) })
    }

    fun updateMapCoordinates(x: Float, y: Float) {
        mapCoordinates.x = x
        mapCoordinates.y = y
    }
}