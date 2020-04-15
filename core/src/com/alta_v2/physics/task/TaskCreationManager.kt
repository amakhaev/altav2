package com.alta_v2.physics.task

import com.alta_v2.physics.executionContext.TiledMapEngineContext
import com.alta_v2.physics.task.moveNpc.MoveNpcChecker
import com.alta_v2.physics.task.moveNpc.MoveNpcTask
import com.alta_v2.physics.task.movePlayer.MovePlayerChecker
import com.alta_v2.physics.task.movePlayer.MovePlayerTask
import com.alta_v2.physics.task.rotatePlayer.RotateNpcChecker
import com.alta_v2.physics.task.rotatePlayer.RotatePersonTask
import com.alta_v2.physics.task.rotatePlayer.RotatePlayerChecker
import com.alta_v2.physics.utils.MovementCalculator
import mu.KotlinLogging

/**
 * Provides mediator to tasks creation manage.
 */
class TaskCreationManager {

    companion object {

        private val log = KotlinLogging.logger {  }

        /**
         * Creates the task that calculates movement of player.
         *
         * @param direction - the direction of movement.
         * @param context   - the engine execution context.
         * @return crated [ResultTiledMapTask] instance or ``null`` if failed creation.
         */
        fun createPlayerMoveTask(direction: MovementDirection, context: TiledMapEngineContext): ResultTiledMapTask? {
            val checker = MovePlayerChecker()
            if (!checker.canTaskBeExecuted(context)) {
                log.trace("Failed to perform movement of focus point on the {} because process is blocked", direction)
                return null
            }
            val targetPoint = MovementCalculator.getTargetPointLocal(
                    direction, context.focusPointLocal.x, context.focusPointLocal.y
            )
            if (!MovementCalculator.canMoveTo(targetPoint.x, targetPoint.y, context.altitudeMap)) {
                log.debug("Failed to run player moving since [{}: {}] is blocked", targetPoint.x, targetPoint.y)
                return null
            }

            return MovePlayerTask(
                    playerId = context.player.id,
                    playerPointLocal = context.player.localPoint,
                    playerView = context.player.view,
                    isPlayerMoving = context.player.isMoving,
                    altitudeMap = context.altitudeMap,
                    direction = direction,
                    focusPointGlobal = context.focusPointGlobal,
                    focusPointLocal = context.focusPointLocal,
                    targetPointLocal = targetPoint
            )
        }

        /**
         * Creates the task to rotate player.
         *
         * @param direction - the direction of movement.
         * @param context   - the engine execution context.
         * @return crated [ResultTiledMapTask] instance or ``null`` if failed creation.
         */
        fun createRotatePlayerTask(direction: MovementDirection, context: TiledMapEngineContext): ResultTiledMapTask? {
            val currentView = MovementDirection.getPersonView(direction)
            return if (RotatePlayerChecker(currentView).canTaskBeExecuted(context)) RotatePersonTask(direction, context.player.view) else null
        }

        /**
         * Creates the task that calculates movement of NPC.
         *
         * @param direction - the direction of movement.
         * @param npcId     - the ID of NPC to be moved.
         * @param context   - the engine execution context.
         * @return created [ResultTiledMapTask] instance or ``null`` if creation failed.
         */
        fun createNpcMoveTask(direction: MovementDirection, npcId: Int, context: TiledMapEngineContext): ResultTiledMapTask? {
            val checker = MoveNpcChecker(npcId)
            if (!checker.canTaskBeExecuted(context)) {
                log.trace("Failed to perform movement of Npc {} to the {} because process is blocked", npcId, direction)
                return null
            }
            val npc = context.npcMap[npcId]
            val targetPoint = MovementCalculator.getTargetPointLocal(
                    direction, npc!!.localPoint.x, npc.localPoint.y
            )
            if (!MovementCalculator.canMoveTo(targetPoint.x, targetPoint.y, context.altitudeMap)) {
                log.debug("Failed to run npc moving since [{}: {}] is blocked", targetPoint.x, targetPoint.y)
                return null
            }

            return MoveNpcTask(
                    direction = direction,
                    npc = npc,
                    targetPointLocal = targetPoint,
                    altitudeMap = context.altitudeMap,
                    focusPointGlobal = context.focusPointGlobal
            )
        }

        /**
         * Creates the task to rotate player.
         *
         * @param direction - the direction of movement.
         * @param npcId     - the ID of NPC to be moved.
         * @param context   - the engine execution context.
         * @return crated [ResultTiledMapTask] instance or ``null`` if failed creation.
         */
        fun createRotateNpcTask(npcId: Int, direction: MovementDirection, context: TiledMapEngineContext): ResultTiledMapTask? {
            val currentView = MovementDirection.getPersonView(direction)
            return if (RotateNpcChecker(npcId, currentView).canTaskBeExecuted(context)) RotatePersonTask(direction, context.npcMap[npcId]!!.view) else null
        }
    }
}