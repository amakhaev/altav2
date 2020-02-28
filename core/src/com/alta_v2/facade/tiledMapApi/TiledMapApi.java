package com.alta_v2.facade.tiledMapApi;

import com.alta_v2.physicsModule.task.MovementDirection;

public interface TiledMapApi {

    /**
     * Tries to perform movement of player.
     *
     * @param direction - the direction of movement to be perform.
     */
    void performPlayerMovement(MovementDirection direction);

}
