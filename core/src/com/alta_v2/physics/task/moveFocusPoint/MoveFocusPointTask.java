package com.alta_v2.physics.task.moveFocusPoint;

import com.alta_v2.physics.executionContext.altitude.AltitudeMap;
import com.alta_v2.physics.executionContext.Tenant;
import com.alta_v2.physics.executionContext.reserveData.ReservablePoint;
import com.alta_v2.physics.task.TiledMapTask;
import com.alta_v2.physics.utils.TiledMapPhysicCalculator;
import com.badlogic.gdx.math.Vector2;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

/**
 * Provides physics logic to move focus point on tiled map.
 */
@Log4j2
public class MoveFocusPointTask implements TiledMapTask {

    private static final float EXECUTION_TIME_SECONDS = 0.2f;

    private final ReservablePoint focusPointLocal;
    private final ReservablePoint focusPointGlobal;
    private final float distanceLengthX;
    private final float distanceLengthY;
    private final Vector2 targetPointGlobal;
    private final Vector2 targetPointLocal;
    private final Tenant tenant = new Tenant("move-focus-point-task");

    private float currentExecutionTime = 0;

    @Getter
    private boolean isCompleted = false;

    public MoveFocusPointTask(ReservablePoint focusPointLocal,
                              ReservablePoint focusPointGlobal,
                              Vector2 targetPointLocal,
                              AltitudeMap altitudeMap) {
        this.focusPointLocal = focusPointLocal;
        this.focusPointGlobal = focusPointGlobal;
        this.targetPointLocal = targetPointLocal;

        this.focusPointLocal.reserve(this.tenant);
        this.focusPointGlobal.reserve(this.tenant);

        this.targetPointGlobal = new Vector2(
                TiledMapPhysicCalculator.centerTileCoordinate(targetPointLocal.x, altitudeMap.getTileWidth()),
                TiledMapPhysicCalculator.centerTileCoordinate(targetPointLocal.y, altitudeMap.getTileHeight())
        );
        this.distanceLengthX = this.targetPointGlobal.x - this.focusPointGlobal.getX();
        this.distanceLengthY = this.targetPointGlobal.y - this.focusPointGlobal.getY();
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
            this.focusPointGlobal.setValue(this.targetPointGlobal, this.tenant);
        } else {
            // calculates the percentage of current time
            float currentPercentage = this.currentExecutionTime / EXECUTION_TIME_SECONDS * 100;

            // value to be used for one act.
            float reduceValueX = TiledMapPhysicCalculator.percentByValue(this.distanceLengthX, currentPercentage);
            float reduceValueY = TiledMapPhysicCalculator.percentByValue(this.distanceLengthY, currentPercentage);

            float currentX = this.focusPointGlobal.getX() == this.targetPointGlobal.x ?
                    this.focusPointGlobal.getX() : this.targetPointGlobal.x - this.distanceLengthX + reduceValueX;

            float currentY = this.focusPointGlobal.getY() == this.targetPointGlobal.y ?
                    this.focusPointGlobal.getY() : this.targetPointGlobal.y - this.distanceLengthY + reduceValueY;

            this.focusPointGlobal.setValue(currentX, currentY, this.tenant);
        }

        this.postAct();
    }

    private void postAct() {
        if (this.focusPointGlobal.getX() != this.targetPointGlobal.x ||
                this.focusPointGlobal.getY() != this.targetPointGlobal.y) {
            return;
        }

        this.focusPointLocal.setValue(this.targetPointLocal, this.tenant).release(this.tenant);
        this.focusPointGlobal.release(this.tenant);
        this.isCompleted = true;
    }

    @Override
    public void destroy() {

    }
}
