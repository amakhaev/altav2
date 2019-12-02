package com.alta_v2.physicsModule;

import com.alta_v2.physicsModule.executionContext.TiledMapEngineContext;
import com.alta_v2.physicsModule.task.MoveFocusPointTask;
import com.alta_v2.physicsModule.task.TiledMapTask;
import com.alta_v2.physicsModule.task.computation.MovementDirection;
import com.alta_v2.physicsModule.utils.TiledMapParser;
import com.alta_v2.physicsModule.utils.TiledMapPhysicCalculator;
import com.alta_v2.renderingModule.tiledMapScreen.TiledMapState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Provides the engine for calculation on tiled map.
 */
@Log4j2
public class TiledMapPhysicEngine {

    private final TiledMapEngineContext context;
    private final List<TiledMapTask> tasks;

    /**
     * Initialize new instance of {@link TiledMapPhysicEngine}.
     *
     * @param focusPointCoordinates - the coordinates of focus point on tiled map.
     * @param mapPath               - the path to tiled map in assets.
     */
    public TiledMapPhysicEngine(Vector2 focusPointCoordinates, String mapPath) {
        this.context = new TiledMapEngineContext(TiledMapParser.parse(mapPath));
        this.context.writeFocusPointLocal(focusPointCoordinates, this.hashCode());
        this.tasks = new CopyOnWriteArrayList<>();
    }

    /**
     * Indicates if focus point can be moved to new coordinates on tiled map.
     */
    public boolean canMoveFocusPoint() {
        return !this.context.getFocusPointLocal().isReserved() && !this.context.getFocusPointGlobal().isReserved();
    }

    /**
     * Initializes coordinates before first rendering.
     */
    public void init() {
        this.context.writeFocusPointGlobal(
                TiledMapPhysicCalculator.centerTileCoordinateX(this.context),
                TiledMapPhysicCalculator.centerTileCoordinateY(this.context),
                this.hashCode()
        );

        this.context.writeActorPointGlobal(
                TiledMapPhysicCalculator.playerCoordinate(this.context.getAltitudeMap().getTileWidth(), Gdx.graphics.getWidth()),
                TiledMapPhysicCalculator.playerCoordinate(this.context.getAltitudeMap().getTileWidth(), Gdx.graphics.getHeight()),
                this.hashCode()
        );
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
        state.updateMapCoordinates(
                this.context.getFocusPointGlobal().getX(), this.context.getFocusPointGlobal().getY()
        );
        state.updateActorCoordinates(
                this.context.getActorPointGlobal().getX(), this.context.getActorPointGlobal().getY()
        );
    }

    public synchronized void performFocusPointMovement(MovementDirection direction) {
        if (direction == null) {
            log.warn("Given direction is null");
            return;
        }

        if (!this.canMoveFocusPoint()) {
            log.warn("Failed to perform movement of focus point on the {} because process is blocked", direction);
            return;
        }

        this.tasks.add(
                new MoveFocusPointTask(
                        direction,
                        this.context.getFocusPointLocal(),
                        this.context.getFocusPointGlobal(),
                        this.context.getAltitudeMap()
                )
        );
    }
}
