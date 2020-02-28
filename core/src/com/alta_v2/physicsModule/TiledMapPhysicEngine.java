package com.alta_v2.physicsModule;

import com.alta_v2.physicsModule.executionContext.Tenant;
import com.alta_v2.physicsModule.executionContext.TiledMapEngineContext;
import com.alta_v2.physicsModule.task.MovementDirection;
import com.alta_v2.physicsModule.task.TaskCreationManager;
import com.alta_v2.physicsModule.task.TiledMapTask;
import com.alta_v2.physicsModule.utils.TiledMapParser;
import com.alta_v2.physicsModule.utils.TiledMapPhysicCalculator;
import com.alta_v2.renderingModule.tiledMapScreen.TiledMapState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
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
                                                       String playerId,
                                                       Map<String, Vector2> npcList) {
        TiledMapPhysicEngine engine = new TiledMapPhysicEngine(focusPointCoordinates, mapPath, playerId);
        if (npcList != null) {
            npcList.forEach((key, value) -> engine.context.addNpc(key, value.x, value.y));
        }
        return engine;
    }

    private final TiledMapEngineContext context;
    private final List<TiledMapTask> tasks;

    /**
     * Initialize new instance of {@link TiledMapPhysicEngine}.
     *
     * @param focusPointCoordinates - the coordinates of focus point on tiled map.
     * @param mapPath               - the path to tiled map in assets.
     */
    private TiledMapPhysicEngine(Vector2 focusPointCoordinates, String mapPath, String playerId) {
        this.context = new TiledMapEngineContext(TiledMapParser.parse(mapPath), playerId);
        this.context.writeFocusPointLocal(focusPointCoordinates);
        this.tasks = new CopyOnWriteArrayList<>();
    }

    /**
     * Initializes coordinates before first rendering.
     */
    public void init() {
        this.context.writeFocusPointGlobal(
                TiledMapPhysicCalculator.centerTileCoordinateX(this.context),
                TiledMapPhysicCalculator.centerTileCoordinateY(this.context)
        );

        this.context.writePlayerPointGlobal(
                this.context.getFocusPointLocal().getX(),
                this.context.getFocusPointLocal().getY(),
                TiledMapPhysicCalculator.playerCoordinate(this.context.getAltitudeMap().getTileWidth(), Gdx.graphics.getWidth()),
                TiledMapPhysicCalculator.playerCoordinate(this.context.getAltitudeMap().getTileWidth(), Gdx.graphics.getHeight())
        );

        Tenant temporary = new Tenant("temporary-npc");
        this.context.getNpcMap().values().forEach(npc -> {
            npc.getGlobalPoint().reserve(temporary).setValue(
                    400 + npc.getLocalPoint().getX() * 32 - this.context.getFocusPointLocal().getX() * 32 - 16,
                    300 + npc.getLocalPoint().getY() * 32 - this.context.getFocusPointLocal().getY() * 32 - 16,
                    temporary
            ).release(temporary);
        });
    }

    /**
     * Acts the tasks that currently active.
     *
     * @param delta - the time in seconds since the last render.
     */
    public void act(float delta) {
        // tasks that were finished should be removed.
        this.tasks.removeIf(TiledMapTask::isCompleted);

        // execute one act of task
        this.tasks.forEach(task -> task.act(delta));
    }

    /**
     * Updates the tiled map state.
     *
     * @param state - the state to be used in rendering.
     */
    public void updateState(TiledMapState state) {
        StateUpdater.updateAll(state, this.context);
    }

    public synchronized void performPlayerMovement(MovementDirection direction) {
        if (direction == null) {
            log.warn("Given direction is null");
            return;
        }

        TiledMapTask task = TaskCreationManager.createPlayerMoveTask(direction, this.context);
        if (task == null) {
            task = TaskCreationManager.createRotatePlayerTask(direction, this.context);
        }

        if (task != null) {
            this.tasks.add(task);
        }
    }
}
