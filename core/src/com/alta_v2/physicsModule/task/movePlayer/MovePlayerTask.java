package com.alta_v2.physicsModule.task.movePlayer;

import com.alta_v2.physicsModule.executionContext.AltitudeMap;
import com.alta_v2.physicsModule.executionContext.Tenant;
import com.alta_v2.physicsModule.executionContext.reserveData.ReservableBoolean;
import com.alta_v2.physicsModule.executionContext.reserveData.ReservablePersonView;
import com.alta_v2.physicsModule.executionContext.reserveData.ReservablePoint;
import com.alta_v2.physicsModule.task.MovementDirection;
import com.alta_v2.physicsModule.task.TiledMapTask;
import com.alta_v2.physicsModule.task.moveFocusPoint.MoveFocusPointTask;
import com.badlogic.gdx.math.Vector2;
import lombok.Builder;
import lombok.Getter;

/**
 * Provides the logic related to movement of player.
 */
public class MovePlayerTask implements TiledMapTask {

    @Builder
    private static MovePlayerTask create(MovementDirection direction,
                                         ReservablePoint focusPointLocal,
                                         ReservablePoint focusPointGlobal,
                                         ReservablePoint playerPointLocal,
                                         Vector2 targetPointLocal,
                                         AltitudeMap altitudeMap,
                                         ReservablePersonView playerView,
                                         ReservableBoolean isPlayerMoving) {
        return new MovePlayerTask(
                direction, focusPointLocal, focusPointGlobal, playerPointLocal, targetPointLocal,
                altitudeMap, playerView, isPlayerMoving
        );
    }

    private final MoveFocusPointTask moveFocusPointTask;
    private final ReservablePoint playerPointLocal;
    private final ReservablePersonView playerView;
    private final ReservableBoolean isPlayerMoving;
    private final AltitudeMap altitudeMap;
    private final int localStartX;
    private final int localStartY;
    private final ReservablePoint focusPointLocal;
    private final Tenant tenant = new Tenant("move-player-task");

    @Getter
    private boolean isCompleted;

    private MovePlayerTask(MovementDirection direction,
                           ReservablePoint focusPointLocal,
                           ReservablePoint focusPointGlobal,
                           ReservablePoint playerPointLocal,
                           Vector2 targetPointLocal,
                           AltitudeMap altitudeMap,
                           ReservablePersonView playerView,
                           ReservableBoolean isPlayerMoving) {
        this.moveFocusPointTask = new MoveFocusPointTask(focusPointLocal, focusPointGlobal, targetPointLocal, altitudeMap);
        this.playerView = playerView;
        this.isPlayerMoving = isPlayerMoving;
        this.altitudeMap = altitudeMap;
        this.playerPointLocal = playerPointLocal;
        this.focusPointLocal = focusPointLocal;
        this.localStartX = (int)focusPointLocal.getX();
        this.localStartY = (int)focusPointLocal.getY();

        this.altitudeMap.setPointStatus((int)targetPointLocal.x, (int)targetPointLocal.y, AltitudeMap.PointAvailability.BARRIER);
        this.playerView.reserve(this.tenant).setValue(MovementDirection.getPersonView(direction), this.tenant);

        this.playerPointLocal.reserve(this.tenant);
        this.isPlayerMoving.reserve(this.tenant).setValue(true, this.tenant);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void act(float delta) {
        if (this.isCompleted) {
            return;
        }

        this.moveFocusPointTask.act(delta);

        if (this.moveFocusPointTask.isCompleted()) {
            this.playerView.release(this.tenant);
            this.isPlayerMoving.setValue(false, this.tenant).release(this.tenant);
            this.playerPointLocal
                    .setValue(this.focusPointLocal.getX(), this.focusPointLocal.getY(), this.tenant)
                    .release(this.tenant);

            this.altitudeMap.setPointStatus(this.localStartX, this.localStartY, AltitudeMap.PointAvailability.FREE);
            this.isCompleted = true;
        }
    }
}
