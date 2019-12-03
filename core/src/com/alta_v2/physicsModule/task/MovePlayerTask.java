package com.alta_v2.physicsModule.task;

import com.alta_v2.physicsModule.executionContext.AltitudeMap;
import com.alta_v2.physicsModule.executionContext.ReservableBoolean;
import com.alta_v2.physicsModule.executionContext.ReservablePersonView;
import com.alta_v2.physicsModule.executionContext.ReservablePoint;
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
                                         AltitudeMap altitudeMap,
                                         ReservablePersonView playerView,
                                         ReservableBoolean isPlayerMoving) {
        return new MovePlayerTask(direction, focusPointLocal, focusPointGlobal, altitudeMap, playerView, isPlayerMoving);
    }

    private final MoveFocusPointTask moveFocusPointTask;
    private final ReservablePersonView playerView;
    private final ReservableBoolean isPlayerMoving;

    @Getter
    private boolean isCompleted;

    private MovePlayerTask(MovementDirection direction,
                           ReservablePoint focusPointLocal,
                           ReservablePoint focusPointGlobal,
                           AltitudeMap altitudeMap,
                           ReservablePersonView playerView,
                           ReservableBoolean isPlayerMoving) {
        this.moveFocusPointTask = new MoveFocusPointTask(direction, focusPointLocal, focusPointGlobal, altitudeMap);
        this.playerView = playerView;
        this.isPlayerMoving = isPlayerMoving;

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
            this.isCompleted = true;
        }
    }
}
