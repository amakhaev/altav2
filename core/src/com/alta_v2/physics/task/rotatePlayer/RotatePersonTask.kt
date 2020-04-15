package com.alta_v2.physics.task.rotatePlayer

import com.alta_v2.physics.executionContext.Tenant
import com.alta_v2.physics.executionContext.reserveData.ReservablePersonView
import com.alta_v2.physics.task.MovementDirection
import com.alta_v2.physics.task.ResultTiledMapTask
import com.alta_v2.physics.task.resultObserver.TaskResult

/**
 * Provides the logic related to rotate of player.
 */
class RotatePersonTask(private val direction: MovementDirection,
                       private val personView: ReservablePersonView) : ResultTiledMapTask {
    private val tenant = Tenant("rotate-person-task")

    private var completed = false
    private val taskResult = TaskResult()

    /**
     * {@inheritDoc}
     */
    override fun act(delta: Float) {
        personView
                .reserve(tenant)
                .setValue(MovementDirection.getPersonView(direction), tenant)
                .release(tenant)
        completed = true
        taskResult.complete(null)
    }

    override fun isCompleted(): Boolean = completed
    override fun getResult(): TaskResult = taskResult

    override fun destroy() {}

}