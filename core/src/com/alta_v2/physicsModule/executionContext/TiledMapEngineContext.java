package com.alta_v2.physicsModule.executionContext;

import com.alta_v2.renderingModule.actors.PersonView;
import com.badlogic.gdx.math.Vector2;
import lombok.Getter;

/**
 * Provides the execution context of tiled map engine.
 */
@Getter
public final class TiledMapEngineContext {

    private final ReservablePoint focusPointLocal;
    private final ReservablePoint focusPointGlobal;
    private final ReservablePoint actorPointGlobal;
    private final AltitudeMap altitudeMap;
    private final ReservablePersonView playerView;
    private final ReservableBoolean isPlayerMoving;

    /**
     * Initialize new instance of {@link TiledMapEngineContext}
     *
     * @param altitudeMap - the {@link AltitudeMap} instance.
     */
    public TiledMapEngineContext(AltitudeMap altitudeMap) {
        this.altitudeMap = altitudeMap;
        this.actorPointGlobal = new ReservablePoint();
        this.focusPointLocal = new ReservablePoint();
        this.focusPointGlobal = new ReservablePoint();
        this.playerView = new ReservablePersonView();
        this.isPlayerMoving = new ReservableBoolean();
    }

    public void writeFocusPointLocal(Vector2 coordinates, int hashCode) {
        this.writePointValue(this.focusPointLocal, coordinates, hashCode);
    }

    public void writeFocusPointGlobal(float x, float y, int hashCode) {
        this.writePointValue(this.focusPointGlobal, x, y, hashCode);
    }

    public void writeActorPointGlobal(float x, float y, int hashCode) {
        this.writePointValue(this.actorPointGlobal, x, y, hashCode);
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
