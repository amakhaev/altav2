package com.alta_v2.physicsModule.task.rotatePlayer;

import com.alta_v2.physicsModule.executionContext.ReservablePersonView;
import com.alta_v2.physicsModule.task.MovementDirection;
import com.alta_v2.physicsModule.task.TiledMapTask;
import lombok.Getter;

/**
 * Provides the logic related to rotate of player.
 */
public class RotatePlayerTask implements TiledMapTask {

    private final MovementDirection direction;
    private final ReservablePersonView playerView;

    @Getter
    private boolean completed;

    public RotatePlayerTask(MovementDirection direction, ReservablePersonView playerView) {
        this.playerView = playerView;
        this.direction = direction;
        this.completed = false;

        this.playerView.reserve(this.hashCode());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void act(float delta) {
        this.playerView.setValue(MovementDirection.getPersonView(direction), this.hashCode());
        this.playerView.release(this.hashCode());
        this.completed = true;
    }
}
