package com.alta_v2.physics.npc;

import com.alta_v2.physics.executionContext.AltitudeMap;
import com.alta_v2.physics.executionContext.Tenant;
import com.alta_v2.physics.executionContext.TiledMapEngineContext;
import com.alta_v2.physics.executionContext.reserveData.ReservableActor;
import com.alta_v2.physics.utils.NpcMovementCalculator;
import lombok.extern.log4j.Log4j2;

import java.util.Map;

@Log4j2
public class NpcActProcessor {

    private final Tenant tenant = new Tenant("npc-act-processor");

    /**
     * Initializes coordinates before first rendering.
     */
    public void processInit(TiledMapEngineContext context) {
        context.getNpcMap().values().forEach(npc -> {
            this.updateGlobalCoordinates(context, npc);

            int localX = (int) npc.getLocalPoint().getX();
            int localY = (int) npc.getLocalPoint().getY();

            if (context.getAltitudeMap().getPointStatus(localX, localY) != AltitudeMap.PointAvailability.NPC) {
                context.getAltitudeMap().setPointStatus(localX, localY, AltitudeMap.PointAvailability.NPC);
            }
        });
    }

    /**
     * Acts the tasks that currently active.
     */
    public void processAct(TiledMapEngineContext context) {
        for (Map.Entry<Integer, ReservableActor> entry : context.getNpcMap().entrySet()) {
            if (!entry.getValue().isReserved()) {
                this.updateGlobalCoordinates(context, entry.getValue());
            }
        }
    }

    private void updateGlobalCoordinates(TiledMapEngineContext context, ReservableActor npc) {
        float globalX = NpcMovementCalculator.getPositionX(
                npc.getLocalPoint().getX(), context.getFocusPointGlobal().getX(), context.getAltitudeMap().getTileWidth()
        );

        float globalY = NpcMovementCalculator.getPositionY(
                npc.getLocalPoint().getY(), context.getFocusPointGlobal().getY(), context.getAltitudeMap().getTileHeight()
        );
        npc.getGlobalPoint().reserve(this.tenant).setValue(globalX, globalY, this.tenant).release(this.tenant);
    }

}
