package com.alta_v2.physics.executionContext

import com.alta_v2.physics.executionContext.altitude.AltitudeMap
import com.alta_v2.physics.executionContext.reserveData.ReservableActor
import com.alta_v2.physics.executionContext.reserveData.ReservablePoint
import com.badlogic.gdx.math.Vector2
import com.google.common.collect.Maps
import mu.KotlinLogging

/**
 * Provides the execution context of tiled map engine.
 */
class TiledMapEngineContext(val altitudeMap: AltitudeMap, playerId: Int) {

    private val log = KotlinLogging.logger {  }
    private val tenant = Tenant("engine-context")

    val focusPointLocal = ReservablePoint()
    val focusPointGlobal = ReservablePoint()
    val player: ReservableActor = ReservableActor(playerId)
    val npcMap: MutableMap<Int, ReservableActor> = Maps.newHashMap()

    fun addNpc(id: Int, localX: Float, localY: Float) {
        if (npcMap.containsKey(id)) {
            log.warn("NPC with given id {} already exists", id)
            return
        }
        val npc = ReservableActor(id)
        npc.localPoint.reserve(tenant).setValue(localX, localY, tenant).release(tenant)
        npcMap[id] = npc
    }

    fun writeFocusPointLocal(coordinates: Vector2) {
        this.writePointValue(focusPointLocal, coordinates)
    }

    fun writeFocusPointGlobal(x: Float, y: Float) {
        this.writePointValue(focusPointGlobal, x, y)
    }

    fun writePlayerPointGlobal(localX: Float, localY: Float, globalX: Float, globalY: Float) {
        this.writePointValue(player.localPoint, localX, localY)
        this.writePointValue(player.globalPoint, globalX, globalY)
    }

    private fun writePointValue(point: ReservablePoint, coordinates: Vector2) {
        point.reserve(tenant).setValue(coordinates, tenant).release(tenant)
    }

    private fun writePointValue(point: ReservablePoint, x: Float, y: Float) {
        point.reserve(tenant).setValue(x, y, tenant).release(tenant)
    }
}