package com.alta_v2.physics.executionContext;

import com.alta_v2.physics.executionContext.reserveData.ReservableActor;
import com.alta_v2.physics.executionContext.reserveData.ReservablePoint;
import com.badlogic.gdx.math.Vector2;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.Map;

/**
 * Provides the execution context of tiled map engine.
 */
@Getter
@Log4j2
public final class TiledMapEngineContext {

    private final AltitudeMap altitudeMap;
    private final ReservablePoint focusPointLocal;
    private final ReservablePoint focusPointGlobal;
    private final ReservableActor player;
    private final Map<String, ReservableActor> npcMap;
    private final Tenant tenant = new Tenant("engine-context");

    /**
     * Initialize new instance of {@link TiledMapEngineContext}
     *
     * @param altitudeMap - the {@link AltitudeMap} instance.
     */
    public TiledMapEngineContext(AltitudeMap altitudeMap, String playerId) {
        this.altitudeMap = altitudeMap;
        this.focusPointLocal = new ReservablePoint();
        this.focusPointGlobal = new ReservablePoint();
        this.player = new ReservableActor(playerId);
        this.npcMap = Maps.newHashMap();
    }

    public void addNpc(String id, float localX, float localY) {
        if (this.npcMap.containsKey(id)) {
            log.warn("NPC with given id {} already exists", id);
            return;
        }

        ReservableActor npc = new ReservableActor(id);
        npc.getLocalPoint().reserve(this.tenant).setValue(localX, localY, tenant).release(this.tenant);
        this.npcMap.put(id, npc);
    }

    public void writeFocusPointLocal(Vector2 coordinates) {
        this.writePointValue(this.focusPointLocal, coordinates);
    }

    public void writeFocusPointGlobal(float x, float y) {
        this.writePointValue(this.focusPointGlobal, x, y);
    }

    public void writePlayerPointGlobal(float localX, float localY, float globalX, float globalY) {
        this.writePointValue(this.player.getLocalPoint(), localX, localY);
        this.writePointValue(this.player.getGlobalPoint(), globalX, globalY);
    }

    private void writePointValue(ReservablePoint point, Vector2 coordinates) {
        point.reserve(this.tenant).setValue(coordinates, this.tenant).release(this.tenant);
    }

    private void writePointValue(ReservablePoint point, float x, float y) {
        point.reserve(this.tenant).setValue(x, y, this.tenant).release(this.tenant);
    }
}
