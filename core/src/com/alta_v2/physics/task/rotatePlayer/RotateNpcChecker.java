package com.alta_v2.physics.task.rotatePlayer;

import com.alta_v2.physics.executionContext.TiledMapEngineContext;
import com.alta_v2.physics.executionContext.reserveData.ReservableActor;
import com.alta_v2.physics.task.ExecutionChecker;
import com.alta_v2.rendering.actors.PersonView;
import lombok.RequiredArgsConstructor;

/**
 * Provides the checker that indicates when NPC can be rotate.
 */
@RequiredArgsConstructor
public class RotateNpcChecker implements ExecutionChecker {

    private final String npcId;
    private final PersonView currentView;

    @Override
    public boolean canTaskBeExecuted(TiledMapEngineContext context) {
        ReservableActor npc = context.getNpcMap().get(this.npcId);
        return !npc.getView().isReserved() && npc.getView().getValue() != this.currentView;
    }
}
