package com.alta_v2.physics.task.moveFocusPoint

import com.alta_v2.physics.executionContext.Tenant
import com.alta_v2.physics.executionContext.altitude.AltitudeMap
import com.alta_v2.physics.executionContext.reserveData.ReservablePoint
import com.alta_v2.physics.task.TiledMapTask
import com.alta_v2.physics.utils.TiledMapPhysicCalculator.Companion.centerTileCoordinate
import com.alta_v2.physics.utils.TiledMapPhysicCalculator.Companion.percentByValue
import com.badlogic.gdx.math.Vector2

/**
 * Provides physics logic to move focus point on tiled map.
 */
class MoveFocusPointTask(private val focusPointLocal: ReservablePoint,
                         private val focusPointGlobal: ReservablePoint,
                         private val targetPointLocal: Vector2,
                         altitudeMap: AltitudeMap) : TiledMapTask {

    private val distanceLengthX: Float
    private val distanceLengthY: Float
    private val targetPointGlobal: Vector2
    private val tenant = Tenant("move-focus-point-task")
    private var currentExecutionTime = 0f

    private var completed = false

    companion object {
        private const val EXECUTION_TIME_SECONDS = 0.2f
    }

    init {
        focusPointLocal.reserve(tenant)
        focusPointGlobal.reserve(tenant)
        targetPointGlobal = Vector2(
                centerTileCoordinate(targetPointLocal.x, altitudeMap.tileWidth),
                centerTileCoordinate(targetPointLocal.y, altitudeMap.tileHeight)
        )
        distanceLengthX = targetPointGlobal.x - focusPointGlobal.x
        distanceLengthY = targetPointGlobal.y - focusPointGlobal.y
    }

    override fun isCompleted(): Boolean = completed

    /**
     * Acts the tasks that currently active.
     *
     * @param delta - the time in seconds since the last render.
     */
    override fun act(delta: Float) {
        currentExecutionTime += delta
        if (currentExecutionTime >= EXECUTION_TIME_SECONDS) {
            focusPointGlobal.setValue(targetPointGlobal, tenant)
        } else {
            // calculates the percentage of current time
            val currentPercentage = currentExecutionTime / EXECUTION_TIME_SECONDS * 100

            // value to be used for one act.
            val reduceValueX = percentByValue(distanceLengthX, currentPercentage)
            val reduceValueY = percentByValue(distanceLengthY, currentPercentage)
            val currentX = if (focusPointGlobal.x == targetPointGlobal.x) focusPointGlobal.x else targetPointGlobal.x - distanceLengthX + reduceValueX
            val currentY = if (focusPointGlobal.y == targetPointGlobal.y) focusPointGlobal.y else targetPointGlobal.y - distanceLengthY + reduceValueY
            focusPointGlobal.setValue(currentX, currentY, tenant)
        }
        postAct()
    }

    private fun postAct() {
        if (focusPointGlobal.x != targetPointGlobal.x ||
                focusPointGlobal.y != targetPointGlobal.y) {
            return
        }
        focusPointLocal.setValue(targetPointLocal, tenant).release(tenant)
        focusPointGlobal.release(tenant)
        completed = true
    }

    override fun destroy() {}
}