package com.alta_v2.facade.tiledMapApi;

import com.alta_v2.physics.task.MovementDirection;
import com.alta_v2.physics.task.resultObserver.TaskResultObserver;

public interface TiledMapCoreApi {

    /**
     * Gets the ID of object which is target of player.
     */
    Integer getPlayerPurpose();

    /**
     * Tries to perform movement of player.
     *
     * @param direction - the direction of movement to be perform.
     */
    TaskResultObserver performPlayerMovement(MovementDirection direction);

    /**
     * Tries to perform movement of NPC with given ID.
     *
     * @param npcId     - the id of NPC to be moved.
     * @param direction - the direction of movement to be perform.
     */
    TaskResultObserver performNpcMovement(int npcId, MovementDirection direction);

}
