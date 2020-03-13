package com.alta_v2.physics.task.rotatePlayer;

import com.alta_v2.physics.executionContext.Tenant;
import com.alta_v2.physics.executionContext.reserveData.ReservablePersonView;
import com.alta_v2.physics.task.MovementDirection;
import com.alta_v2.physics.task.ResultTiledMapTask;
import com.alta_v2.physics.task.TiledMapTask;
import com.alta_v2.physics.task.resultObserver.BaseTaskResultObserver;
import lombok.Getter;

/**
 * Provides the logic related to rotate of player.
 */
public class RotatePersonTask implements ResultTiledMapTask {

    private final MovementDirection direction;
    private final ReservablePersonView personView;
    private final Tenant tenant = new Tenant("rotate-person-task");

    @Getter
    private boolean completed;

    @Getter
    private BaseTaskResultObserver result = new BaseTaskResultObserver();

    public RotatePersonTask(MovementDirection direction, ReservablePersonView personView) {
        this.personView = personView;
        this.direction = direction;
        this.completed = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void act(float delta) {
        this.personView
                .reserve(this.tenant)
                .setValue(MovementDirection.getPersonView(direction), this.tenant)
                .release(this.tenant);
        this.completed = true;

        this.result.submitComplete();
    }

    @Override
    public void destroy() {
        this.result.destroy();
        this.result = null;
    }
}
