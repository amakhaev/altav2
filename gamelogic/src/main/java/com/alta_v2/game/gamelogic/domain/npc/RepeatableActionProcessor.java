package com.alta_v2.game.gamelogic.domain.npc;

import com.alta_v2.common.Destroyable;
import com.alta_v2.game.gamelogic.data.npc.NpcModel;

/**
 * Describes the processor of repeatable action of NPC.
 */
public interface RepeatableActionProcessor extends Destroyable {

    /**
     * Adds given NPC into list. Each item of list tried to perform movement to random direction time to time.
     *
     * @param npc - the {@link NpcModel} to be used for repeatable movement.
     */
    void addToRandomMovement(NpcModel npc);

    /**
     * Starts processing of random movement.
     */
    void startAsync();
}
