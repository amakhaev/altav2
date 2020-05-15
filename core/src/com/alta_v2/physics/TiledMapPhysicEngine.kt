package com.alta_v2.physics

import com.alta_v2.physics.executionContext.TiledMapEngineContext
import com.alta_v2.physics.npc.NpcActProcessor
import com.alta_v2.physics.task.MovementDirection
import com.alta_v2.physics.task.TaskCreationManager
import com.alta_v2.physics.task.TiledMapTask
import com.alta_v2.physics.task.resultObserver.TaskResult
import com.alta_v2.physics.utils.TiledMapParser
import com.alta_v2.physics.utils.TiledMapPhysicCalculator
import com.alta_v2.rendering.tiledMapScreen.TiledMapState
import com.alta_v2.rendering.tiledMapScreen.actors.PersonView
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2
import mu.KotlinLogging
import java.util.concurrent.CopyOnWriteArrayList

/**
 * Provides the engine for calculation on tiled map.
 */
class TiledMapPhysicEngine constructor(focusPointCoordinates: Vector2,
                                       mapPath: String,
                                       playerId: Int,
                                       npcList: Map<Int, Vector2>) : PhysicEngine {

    private val log = KotlinLogging.logger {  }

    private val context = TiledMapEngineContext(TiledMapParser.parse(mapPath), playerId)
    private val tasks: MutableList<TiledMapTask> = CopyOnWriteArrayList()
    private val npcProcessor = NpcActProcessor()

    init {
        context.writeFocusPointLocal(focusPointCoordinates)
        npcList.forEach{ (key, value) -> context.addNpc(key, value.x, value.y)}
    }

    /**
     * Initializes coordinates before first rendering.
     */
    fun processInit() {
        context.writeFocusPointGlobal(
                TiledMapPhysicCalculator.centerTileCoordinateX(context),
                TiledMapPhysicCalculator.centerTileCoordinateY(context)
        )
        context.writePlayerPointGlobal(
                context.focusPointLocal.x,
                context.focusPointLocal.y,
                TiledMapPhysicCalculator.playerCoordinate(context.altitudeMap.tileWidth.toFloat(), Gdx.graphics.width.toFloat()),
                TiledMapPhysicCalculator.playerCoordinate(context.altitudeMap.tileHeight.toFloat(), Gdx.graphics.height.toFloat())
        )
        context.altitudeMap.markAsObject(context.focusPointLocal.x.toInt(), context.focusPointLocal.y.toInt(), context.player.id)
        npcProcessor.processInit(context)
    }

    /**
     * Acts the tasks that currently active.
     *
     * @param delta - the time in seconds since the last render.
     */
    fun processAct(delta: Float) {
        // tasks that were finished should be removed.
        tasks.removeIf { obj: TiledMapTask -> obj.isCompleted() }

        // execute one act of task
        for (task in tasks) {
            task.act(delta)
            if (task.isCompleted()) {
                task.destroy()
            }
        }
        npcProcessor.processAct(context)
    }

    /**
     * Updates the tiled map state.
     *
     * @param state - the state to be used in rendering.
     */
    fun updateState(state: TiledMapState) {
        StateUpdater.updateAll(state, context)
    }

    /**
     * Gets the ID of object which is target of player.
     */
    val playerPurpose: Int?
        get() {
            if (context.player.isReserved()) {
                log.warn("Player currently performing movement. No target at this time.")
                return null
            }
            val player = context.player
            var targetX = player.localPoint.x.toInt()
            var targetY = player.localPoint.y.toInt()

            when (player.view.getValue()) {
                PersonView.BACK_VIEW -> targetY += 1
                PersonView.FULL_FACE -> targetY -= 1
                PersonView.SIDE_VIEW_LEFT -> targetX -= 1
                PersonView.SIDE_VIEW_RIGHT -> targetX += 1
            }
            return context.altitudeMap.getObjectId(targetX, targetY)
        }

    @Synchronized
    fun performPlayerMovement(direction: MovementDirection?): TaskResult? {
        if (direction == null || direction == MovementDirection.UNKNOWN) {
            log.warn("Given direction is null or has UNKNOWN value")
            return null
        }
        var task = TaskCreationManager.createPlayerMoveTask(direction, context)
        if (task == null) {
            task = TaskCreationManager.createRotatePlayerTask(direction, context)
        }
        return if (task != null) {
            tasks.add(task)
            task.getResult()
        } else {
            null
        }
    }

    @Synchronized
    fun performNpcMovement(npcId: Int, direction: MovementDirection?): TaskResult? {
        if (direction == null || direction == MovementDirection.UNKNOWN) {
            log.warn("Given params are invalid, npcId: {}, direction: {}", npcId, direction)
            return null
        }
        var task = TaskCreationManager.createNpcMoveTask(direction, npcId, context)
        if (task == null) {
            task = TaskCreationManager.createRotateNpcTask(npcId, direction, context)
        }
        return if (task != null) {
            tasks.add(task)
            task.getResult()
        } else {
            null
        }
    }
}