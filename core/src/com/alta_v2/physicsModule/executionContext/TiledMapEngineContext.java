package com.alta_v2.physicsModule.executionContext;

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

    public void addNpc(String id, float x, float y) {
        if (this.npcMap.containsKey(id)) {
            // log.warn();
            return;
        }
    }

    public void writeFocusPointLocal(Vector2 coordinates, int hashCode) {
        this.writePointValue(this.focusPointLocal, coordinates, hashCode);
    }

    public void writeFocusPointGlobal(float x, float y, int hashCode) {
        this.writePointValue(this.focusPointGlobal, x, y, hashCode);
    }

    public void writePlayerPointGlobal(float x, float y, int hashCode) {
        this.writePointValue(this.player.getGlobalPoint(), x, y, hashCode);
    }

    private void writePointValue(ReservablePoint point, Vector2 coordinates, int hashCode) {
        point.reserve(hashCode);
        point.setValue(coordinates, hashCode);
        point.release(hashCode);
    }

    private void writePointValue(ReservablePoint point, float x, float y, int hashCode) {
        point.reserve(hashCode);
        point.setValue(x, y, hashCode);
        point.release(hashCode);
    }
}
