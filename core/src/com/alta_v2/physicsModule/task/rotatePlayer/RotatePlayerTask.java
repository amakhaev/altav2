package com.alta_v2.physicsModule.task.rotatePlayer;

import com.alta_v2.physicsModule.executionContext.Tenant;
import com.alta_v2.physicsModule.executionContext.reserveData.ReservablePersonView;
import com.alta_v2.physicsModule.task.MovementDirection;
import com.alta_v2.physicsModule.task.TiledMapTask;
import lombok.Getter;

/**
 * Provides the logic related to rotate of player.
 */
public class RotatePlayerTask implements TiledMapTask {

    private final MovementDirection direction;
    private final ReservablePersonView playerView;
    private final Tenant tenant = new Tenant("rotate-player-task");

    @Getter
    private boolean completed;

    public RotatePlayerTask(MovementDirection direction, ReservablePersonView playerView) {
        this.playerView = playerView;
        this.direction = direction;
        this.completed = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void act(float delta) {
        this.playerView
                .reserve(this.tenant)
                .setValue(MovementDirection.getPersonView(direction), this.tenant)
                .release(this.tenant);
        this.completed = true;
    }
}
