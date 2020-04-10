package com.alta_v2.physics;

import com.alta_v2.physics.executionContext.TiledMapEngineContext;
import com.alta_v2.physics.npc.NpcActProcessor;
import com.alta_v2.physics.task.MovementDirection;
import com.alta_v2.physics.task.ResultTiledMapTask;
import com.alta_v2.physics.task.TaskCreationManager;
import com.alta_v2.physics.task.TiledMapTask;
import com.alta_v2.physics.task.resultObserver.TaskResultObserver;
import com.alta_v2.physics.utils.TiledMapParser;
import com.alta_v2.physics.utils.TiledMapPhysicCalculator;
import com.alta_v2.rendering.tiledMapScreen.TiledMapState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.google.common.base.Strings;
import lombok.Builder;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Provides the engine for calculation on tiled map.
 */
@Log4j2
public class TiledMapPhysicEngine implements PhysicEngine {

    @Builder
    private static TiledMapPhysicEngine createInstance(Vector2 focusPointCoordinates,
                                                       String mapPath,
                                                       int playerId,
                                                       Map<Integer, Vector2> npcList) {
        TiledMapPhysicEngine engine = new TiledMapPhysicEngine(focusPointCoordinates, mapPath, playerId);
        if (npcList != null) {
            npcList.forEach((key, value) -> engine.context.addNpc(key, value.x, value.y));
        }
        return engine;
    }

    private final TiledMapEngineContext context;
    private final List<TiledMapTask> tasks;
    private final NpcActProcessor npcProcessor;

    /**
     * Initialize new instance of {@link TiledMapPhysicEngine}.
     *
     * @param focusPointCoordinates - the coordinates of focus point on tiled map.
     * @param mapPath               - the path to tiled map in assets.
     */
    private TiledMapPhysicEngine(Vector2 focusPointCoordinates, String mapPath, int playerId) {
        this.context = new TiledMapEngineContext(TiledMapParser.parse(mapPath), playerId);
        this.context.writeFocusPointLocal(focusPointCoordinates);
        this.tasks = new CopyOnWriteArrayList<>();
        this.npcProcessor = new NpcActProcessor();
    }

    /**
     * Initializes coordinates before first rendering.
     */
    public void processInit() {
        this.context.writeFocusPointGlobal(
                TiledMapPhysicCalculator.centerTileCoordinateX(this.context),
                TiledMapPhysicCalculator.centerTileCoordinateY(this.context)
        );

        this.context.writePlayerPointGlobal(
                this.context.getFocusPointLocal().getX(),
                this.context.getFocusPointLocal().getY(),
                TiledMapPhysicCalculator.playerCoordinate(this.context.getAltitudeMap().getTileWidth(), Gdx.graphics.getWidth()),
                TiledMapPhysicCalculator.playerCoordinate(this.context.getAltitudeMap().getTileHeight(), Gdx.graphics.getHeight())
        );

        this.npcProcessor.processInit(this.context);
    }

    /**
     * Acts the tasks that currently active.
     *
     * @param delta - the time in seconds since the last render.
     */
    public void processAct(float delta) {
        // tasks that were finished should be removed.
        this.tasks.removeIf(TiledMapTask::isCompleted);

        // execute one act of task
        for (TiledMapTask task : tasks) {
            task.act(delta);
            if (task.isCompleted()) {
                task.destroy();
            }
        }

        this.npcProcessor.processAct(this.context);
    }

    /**
     * Updates the tiled map state.
     *
     * @param state - the state to be used in rendering.
     */
    public void updateState(TiledMapState state) {
        StateUpdater.updateAll(state, this.context);
    }

    public synchronized TaskResultObserver performPlayerMovement(MovementDirection direction) {
        if (direction == null) {
            log.warn("Given direction is null");
            return null;
        }

        ResultTiledMapTask task = TaskCreationManager.createPlayerMoveTask(direction, this.context);
        if (task == null) {
            task = TaskCreationManager.createRotatePlayerTask(direction, this.context);
        }

        if (task != null) {
            this.tasks.add(task);
            return task.getResult();
        } else {
            return null;
        }
    }

    public synchronized TaskResultObserver performNpcMovement(int npcId, MovementDirection direction) {
        if (direction == null) {
            log.warn("Given params are invalid, npcId: {}, direction: {}", npcId, direction);
            return null;
        }

        ResultTiledMapTask task = TaskCreationManager.createNpcMoveTask(direction, npcId, this.context);
        if (task == null) {
            task = TaskCreationManager.createRotateNpcTask(npcId, direction, this.context);
        }

        if (task != null) {
            this.tasks.add(task);
            return task.getResult();
        } else {
            return null;
        }
    }
}
