package com.alta_v2.physics.task.moveNpc

import com.alta_v2.physics.executionContext.Tenant
import com.alta_v2.physics.executionContext.altitude.AltitudeMap
import com.alta_v2.physics.executionContext.reserveData.ReservableActor
import com.alta_v2.physics.executionContext.reserveData.ReservablePoint
import com.alta_v2.physics.task.MovementDirection
import com.alta_v2.physics.task.ResultTiledMapTask
import com.alta_v2.physics.task.resultObserver.TaskResult
import com.alta_v2.physics.utils.NpcMovementCalculator
import com.alta_v2.physics.utils.TiledMapPhysicCalculator.Companion.localToGlobal
import com.alta_v2.physics.utils.TiledMapPhysicCalculator.Companion.percentByValue
import com.badlogic.gdx.math.Vector2

class MoveNpcTask(direction: MovementDirection,
                  private val npc: ReservableActor,
                  private val targetPointLocal: Vector2,
                  private val altitudeMap: AltitudeMap,
                  private val focusPointGlobal: ReservablePoint) : ResultTiledMapTask {

    companion object {
        private const val EXECUTION_TIME_SECONDS = 0.2f
    }

    private val targetPointGlobal = Vector2(
            localToGlobal(targetPointLocal.x, altitudeMap.tileWidth),
            localToGlobal(targetPointLocal.y, altitudeMap.tileHeight)
    )
    private val distanceLengthX: Float = (targetPointLocal.x - npc.localPoint.x) * altitudeMap.tileWidth
    private val distanceLengthY: Float = (targetPointLocal.y - npc.localPoint.y) * altitudeMap.tileHeight
    private val tenant = Tenant("move-npc-task")
    private var currentExecutionTime = 0f

    private var completed = false
    private val taskResult = TaskResult()

    init {
        altitudeMap.markAsBarrier(targetPointLocal.x.toInt(), targetPointLocal.y.toInt())
        npc.reserve(tenant)
        npc.localPoint.reserve(tenant)
        npc.globalPoint.reserve(tenant)
        npc.view.reserve(tenant).setValue(MovementDirection.getPersonView(direction), tenant)
    }

    override fun act(delta: Float) {
        if (isCompleted()) {
            return
        }
        currentExecutionTime += delta
        if (currentExecutionTime >= EXECUTION_TIME_SECONDS) {
            npc.globalPoint.setValue(targetPointGlobal, tenant)
        } else {
            // calculates the percentage of current time
            val currentPercentage = currentExecutionTime / EXECUTION_TIME_SECONDS * 100

            // value to be used for one act.
            val reduceValueX = percentByValue(distanceLengthX, currentPercentage)
            val reduceValueY = percentByValue(distanceLengthY, currentPercentage)
            val globalX = NpcMovementCalculator.getPositionX(
                    npc.localPoint.x, focusPointGlobal.x, altitudeMap.tileWidth
            )
            val globalY = NpcMovementCalculator.getPositionY(
                    npc.localPoint.y, focusPointGlobal.y, altitudeMap.tileHeight
            )
            val currentX = if (npc.globalPoint.x == targetPointGlobal.x) npc.globalPoint.x else globalX + reduceValueX
            val currentY = if (npc.globalPoint.y == targetPointGlobal.y) npc.globalPoint.y else globalY + reduceValueY
            npc.globalPoint.setValue(currentX, currentY, tenant)
        }
        postAct()
    }

    override fun isCompleted(): Boolean = completed

    override fun getResult(): TaskResult = taskResult

    private fun postAct() {
        if (npc.globalPoint.x != targetPointGlobal.x ||
                npc.globalPoint.y != targetPointGlobal.y) {
            return
        }
        altitudeMap.markAsFree(npc.localPoint.x.toInt(), npc.localPoint.y.toInt())
        altitudeMap.markAsObject(targetPointLocal.x.toInt(), targetPointLocal.y.toInt(), npc.id)
        npc.localPoint.setValue(targetPointLocal, tenant).release(tenant)
        npc.globalPoint.release(tenant)
        npc.view.release(tenant)
        npc.release(tenant)
        completed = true
        taskResult.complete(null)
    }

    override fun destroy() {
    }
}