package com.alta_v2.renderingModule.tiledMapScreen;

import com.alta_v2.renderingModule.ScreenState;
import com.badlogic.gdx.math.Vector2;
import lombok.Getter;

/**
 * Provides the current state of tiled map.
 */
public class TiledMapState implements ScreenState {

    @Getter
    private Vector2 mapCoordinates = new Vector2();

    @Getter
    private Vector2 actorCoordinates = new Vector2();

    public void updateMapCoordinates(float x, float y) {
        this.mapCoordinates.x = x;
        this.mapCoordinates.y = y;
    }

    public void updateActorCoordinates(float x, float y) {
        this.actorCoordinates.x = x;
        this.actorCoordinates.y = y;
    }
}
