package com.alta_v2.physics.task.movePlayer

import com.alta_v2.physics.executionContext.Tenant
import com.alta_v2.physics.executionContext.altitude.AltitudeMap
import com.alta_v2.physics.executionContext.reserveData.ReservableBoolean
import com.alta_v2.physics.executionContext.reserveData.ReservablePersonView
import com.alta_v2.physics.executionContext.reserveData.ReservablePoint
import com.alta_v2.physics.task.MovementDirection
import com.alta_v2.physics.task.ResultTiledMapTask
import com.alta_v2.physics.task.moveFocusPoint.MoveFocusPointTask
import com.alta_v2.physics.task.resultObserver.TaskResult
import com.badlogic.gdx.math.Vector2

/**
 * Provides the logic related to movement of player.
 */
class MovePlayerTask(direction: MovementDirection,
                     focusPointGlobal: ReservablePoint,
                     private val playerId: Int,
                     private val targetPointLocal: Vector2,
                     private val focusPointLocal: ReservablePoint,
                     private val playerPointLocal: ReservablePoint,
                     private val altitudeMap: AltitudeMap,
                     private val playerView: ReservablePersonView,
                     private val isPlayerMoving: ReservableBoolean) : ResultTiledMapTask {

    private val moveFocusPointTask = MoveFocusPointTask(focusPointLocal, focusPointGlobal, targetPointLocal, altitudeMap)
    private val localStartX: Int = focusPointLocal.x.toInt()
    private val localStartY: Int = focusPointLocal.y.toInt()

    private val tenant = Tenant("move-player-task")
    private var completed = false
    private val taskResult = TaskResult()

    init {
        this.altitudeMap.markAsBarrier(targetPointLocal.x.toInt(), targetPointLocal.y.toInt())
        this.playerView.reserve(tenant).setValue(MovementDirection.getPersonView(direction), tenant)
        this.playerPointLocal.reserve(tenant)
        this.isPlayerMoving.reserve(tenant).setValue(true, tenant)
    }

    /**
     * {@inheritDoc}
     */
    override fun act(delta: Float) {
        if (isCompleted()) {
            return
        }
        moveFocusPointTask.act(delta)
        if (moveFocusPointTask.isCompleted()) {
            playerView.release(tenant)
            isPlayerMoving.setValue(false, tenant).release(tenant)
            playerPointLocal
                    .setValue(focusPointLocal.x, focusPointLocal.y, tenant)
                    .release(tenant)
            altitudeMap.markAsFree(localStartX, localStartY)
            this.altitudeMap.markAsObject(targetPointLocal.x.toInt(), targetPointLocal.y.toInt(), playerId)
            completed = true
            taskResult.complete(null)
        }
    }

    override fun getResult(): TaskResult = taskResult

    override fun isCompleted(): Boolean = completed

    override fun destroy() {}
}