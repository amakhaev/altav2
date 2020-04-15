package com.alta_v2.physics.npc

import com.alta_v2.physics.executionContext.Tenant
import com.alta_v2.physics.executionContext.TiledMapEngineContext
import com.alta_v2.physics.executionContext.reserveData.ReservableActor
import com.alta_v2.physics.utils.NpcMovementCalculator.Companion.getPositionX
import com.alta_v2.physics.utils.NpcMovementCalculator.Companion.getPositionY
import java.util.function.Consumer

class NpcActProcessor {

    private val tenant = Tenant("npc-act-processor")

    /**
     * Initializes coordinates before first rendering.
     */
    fun processInit(context: TiledMapEngineContext) {
        context.npcMap.values.forEach(Consumer { npc: ReservableActor ->
            updateGlobalCoordinates(context, npc)
            val localX = npc.localPoint.x.toInt()
            val localY = npc.localPoint.y.toInt()
            context.altitudeMap.markAsObject(localX, localY, npc.id)
        })
    }

    /**
     * Acts the tasks that currently active.
     */
    fun processAct(context: TiledMapEngineContext) {
        for ((_, value) in context.npcMap) {
            if (!value.isReserved()) {
                updateGlobalCoordinates(context, value)
            }
        }
    }

    private fun updateGlobalCoordinates(context: TiledMapEngineContext, npc: ReservableActor) {
        val globalX = getPositionX(
                npc.localPoint.x, context.focusPointGlobal.x, context.altitudeMap.tileWidth
        )
        val globalY = getPositionY(
                npc.localPoint.y, context.focusPointGlobal.y, context.altitudeMap.tileHeight
        )
        npc.globalPoint.reserve(tenant).setValue(globalX, globalY, tenant).release(tenant)
    }
}