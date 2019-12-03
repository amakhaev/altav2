package com.alta_v2.physicsModule.task;

import com.alta_v2.physicsModule.executionContext.AltitudeMap;
import com.alta_v2.physicsModule.executionContext.ReservablePoint;
import com.alta_v2.physicsModule.utils.TiledMapPhysicCalculator;
import com.badlogic.gdx.math.Vector2;
import lombok.extern.log4j.Log4j2;

/**
 * Provides physics logic to move focus point on tiled map.
 */
@Log4j2
public class MoveFocusPointTask implements TiledMapTask {

    private final float EXECUTION_TIME_SECONDS = 0.2f;

    private final ReservablePoint focusPointLocal;
    private final ReservablePoint focusPointGlobal;
    private final int distanceLength;

    private Vector2 targetPointLocal;
    private Vector2 targetPointGlobal;
    private float currentExecutionTime;
    private boolean isCompleted;

    public MoveFocusPointTask(MovementDirection direction,
                              ReservablePoint focusPointLocal,
                              ReservablePoint focusPointGlobal,
                              AltitudeMap altitudeMap) {
        this.focusPointLocal = focusPointLocal;
        this.focusPointGlobal = focusPointGlobal;

        this.focusPointLocal.reserve(this.hashCode());
        this.focusPointGlobal.reserve(this.hashCode());

        this.distanceLength = this.getDistanceLength(direction, altitudeMap);
        this.targetPointLocal = this.getTargetPointLocal(direction);
        this.targetPointGlobal = new Vector2(
                TiledMapPhysicCalculator.centerTileCoordinate(this.targetPointLocal.x, altitudeMap.getTileWidth()),
                TiledMapPhysicCalculator.centerTileCoordinate(this.targetPointLocal.y, altitudeMap.getTileHeight())
        );
        this.currentExecutionTime = 0;
        this.isCompleted = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCompleted() {
        return this.isCompleted;
    }

    /**
     * Acts the tasks that currently active.
     *
     * @param delta - the time in seconds since the last render.
     */
    @Override
    public void act(float delta) {
        this.currentExecutionTime += delta;
        if (this.currentExecutionTime >= EXECUTION_TIME_SECONDS) {
            this.focusPointGlobal.setValue(this.targetPointGlobal, this.hashCode());
        } else {
            // calculates the percentage of current time
            float currentPercentage = this.currentExecutionTime / EXECUTION_TIME_SECONDS * 100;

            // value to be used for one act.
            float reduceValue = TiledMapPhysicCalculator.percentByValue(this.distanceLength, currentPercentage);

            float currentX = this.focusPointGlobal.getX() == this.targetPointGlobal.x ?
                    this.focusPointGlobal.getX() : this.targetPointGlobal.x - this.distanceLength + reduceValue;

            float currentY = this.focusPointGlobal.getY() == this.targetPointGlobal.y ?
                    this.focusPointGlobal.getY() : this.targetPointGlobal.y - this.distanceLength + reduceValue;

            this.focusPointGlobal.setValue(currentX, currentY, this.hashCode());
        }

        this.postAct();
    }

    private void postAct() {
        if (this.focusPointGlobal.getX() != this.targetPointGlobal.x ||
                this.focusPointGlobal.getY() != this.targetPointGlobal.y) {
            return;
        }

        this.focusPointLocal.setValue(this.targetPointLocal, this.hashCode());
        this.focusPointLocal.release(this.hashCode());
        this.focusPointGlobal.release(this.hashCode());
        this.isCompleted = true;
    }

    private int getDistanceLength(MovementDirection direction, AltitudeMap altitudeMap) {
        switch (direction) {
            case LEFT:
                return -altitudeMap.getTileWidth();
            case RIGHT:
                return altitudeMap.getTileWidth();
            case HIGHER:
                return altitudeMap.getTileHeight();
            case LOWER:
                return -altitudeMap.getTileHeight();
        }

        throw new RuntimeException("Unknown type of movement direction: " + direction);
    }

    private Vector2 getTargetPointLocal(MovementDirection direction) {
        float currentX = this.focusPointLocal.getX();
        float currentY = this.focusPointLocal.getY();

        switch (direction) {
            case LEFT:
                currentX--;
                break;
            case RIGHT:
                currentX++;
                break;
            case HIGHER:
                currentY++;
                break;
            case LOWER:
                currentY--;
                break;
        }

        return new Vector2(currentX, currentY);
    }
}
