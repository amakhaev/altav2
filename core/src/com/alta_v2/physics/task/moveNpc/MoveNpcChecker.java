package com.alta_v2.physics.task.moveNpc;

import com.alta_v2.physics.executionContext.TiledMapEngineContext;
import com.alta_v2.physics.executionContext.reserveData.ReservableActor;
import com.alta_v2.physics.task.ExecutionChecker;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
public class MoveNpcChecker implements ExecutionChecker {

    private final String npcId;

    @Override
    public boolean canTaskBeExecuted(TiledMapEngineContext context) {
        if (!context.getNpcMap().containsKey(this.npcId)) {
            log.error("Given npcId: {} not found in context", this.npcId);
            return false;
        }

        ReservableActor npc = context.getNpcMap().get(npcId);
        return !npc.isReserved() && !npc.getGlobalPoint().isReserved() && !npc.getLocalPoint().isReserved();
    }
}
