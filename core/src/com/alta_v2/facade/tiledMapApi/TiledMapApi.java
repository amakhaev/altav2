package com.alta_v2.facade.tiledMapApi;

import com.alta_v2.physics.task.MovementDirection;

public interface TiledMapApi {

    /**
     * Tries to perform movement of player.
     *
     * @param direction - the direction of movement to be perform.
     */
    void performPlayerMovement(MovementDirection direction);

    /**
     * Tries to perform movement of NPC with given ID.
     *
     * @param npcId     - the id of NPC to be moved.
     * @param direction - the direction of movement to be perform.
     */
    void performNpcMovement(String npcId, MovementDirection direction);

}
