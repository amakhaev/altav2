package com.alta_v2.physicsModule.task.movePlayer;

import com.alta_v2.physicsModule.executionContext.AltitudeMap;
import com.alta_v2.physicsModule.executionContext.ReservableBoolean;
import com.alta_v2.physicsModule.executionContext.ReservablePersonView;
import com.alta_v2.physicsModule.executionContext.ReservablePoint;
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
                                         Vector2 targetPointLocal,
                                         AltitudeMap altitudeMap,
                                         ReservablePersonView playerView,
                                         ReservableBoolean isPlayerMoving) {
        return new MovePlayerTask(direction, focusPointLocal, focusPointGlobal, targetPointLocal, altitudeMap, playerView, isPlayerMoving);
    }

    private final MoveFocusPointTask moveFocusPointTask;
    private final ReservablePersonView playerView;
    private final ReservableBoolean isPlayerMoving;
    private final AltitudeMap altitudeMap;
    private final int startX;
    private final int startY;

    @Getter
    private boolean isCompleted;

    private MovePlayerTask(MovementDirection direction,
                           ReservablePoint focusPointLocal,
                           ReservablePoint focusPointGlobal,
                           Vector2 targetPointLocal,
                           AltitudeMap altitudeMap,
                           ReservablePersonView playerView,
                           ReservableBoolean isPlayerMoving) {
        this.moveFocusPointTask = new MoveFocusPointTask(focusPointLocal, focusPointGlobal, targetPointLocal, altitudeMap);
        this.playerView = playerView;
        this.isPlayerMoving = isPlayerMoving;
        this.altitudeMap = altitudeMap;
        this.startX = (int)focusPointLocal.getX();
        this.startY = (int)focusPointLocal.getY();

        this.altitudeMap.setPointStatus((int)targetPointLocal.x, (int)targetPointLocal.y, AltitudeMap.PointAvailability.BARRIER);
        this.playerView.reserve(this.hashCode());
        this.playerView.setValue(MovementDirection.getPersonView(direction), this.hashCode());

        this.isPlayerMoving.reserve(this.hashCode());
        this.isPlayerMoving.setValue(true, this.hashCode());
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
            this.playerView.release(this.hashCode());

            this.isPlayerMoving.setValue(false, this.hashCode());
            this.isPlayerMoving.release(this.hashCode());
            this.altitudeMap.setPointStatus(startX, startY, AltitudeMap.PointAvailability.FREE);
            this.isCompleted = true;
        }
    }
}
