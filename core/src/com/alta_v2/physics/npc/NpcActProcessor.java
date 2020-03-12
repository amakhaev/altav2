package com.alta_v2.physics.npc;

import com.alta_v2.physics.executionContext.AltitudeMap;
import com.alta_v2.physics.executionContext.Tenant;
import com.alta_v2.physics.executionContext.TiledMapEngineContext;
import com.alta_v2.physics.executionContext.reserveData.ReservableActor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class NpcActProcessor {

    private final Tenant tenant = new Tenant("npc-act-processor");
    private Vector2 startCoordinates;

    /**
     * Initializes coordinates before first rendering.
     */
    public void processInit(TiledMapEngineContext context) {
        this.startCoordinates = new Vector2((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2);
        updateCoordinatesForAll(context);
    }

    /**
     * Acts the tasks that currently active.
     */
    public void processAct(TiledMapEngineContext context) {
        updateCoordinatesForAll(context);
    }

    private void updateCoordinatesForAll(TiledMapEngineContext context) {
        context.getNpcMap().values().forEach(npc -> {
            this.updateGlobalCoordinates(context, npc);
            this.updateAltitudeMap(context.getAltitudeMap(), npc);
        });
    }

    private void updateGlobalCoordinates(TiledMapEngineContext context, ReservableActor npc) {
        float globalX = this.getPosition(
                startCoordinates.x, npc.getLocalPoint().getX(),
                context.getFocusPointGlobal().getX(), context.getAltitudeMap().getTileWidth()
        );

        float globalY = this.getPosition(
                startCoordinates.y, npc.getLocalPoint().getY(),
                context.getFocusPointGlobal().getY(), context.getAltitudeMap().getTileHeight()
        );
        npc.getGlobalPoint().reserve(this.tenant).setValue(globalX, globalY, this.tenant).release(this.tenant);
    }

    private void updateAltitudeMap(AltitudeMap altitudeMap, ReservableActor npc) {
        int localX = (int) npc.getLocalPoint().getX();
        int localY = (int) npc.getLocalPoint().getY();

        if (altitudeMap.getPointStatus(localX, localY) != AltitudeMap.PointAvailability.NPC) {
            altitudeMap.setPointStatus(localX, localY, AltitudeMap.PointAvailability.NPC);
        }
    }

    private float getPosition(float center, float local, float globalFocus, int tileSize) {
        return local * tileSize + center - globalFocus;
    }
}
