package com.alta_v2.renderingModule.tiledMapScreen;

import com.alta_v2.renderingModule.ScreenState;
import com.alta_v2.renderingModule.tiledMapScreen.state.NpcState;
import com.alta_v2.renderingModule.tiledMapScreen.state.PlayerState;
import com.badlogic.gdx.math.Vector2;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides the current state of tiled map.
 */
public class TiledMapState implements ScreenState {

    @Getter
    private final Vector2 mapCoordinates = new Vector2();

    @Getter
    private final PlayerState player = new PlayerState();

    @Getter
    private final List<NpcState> npcStateList = new ArrayList<>();

    public void updateMapCoordinates(float x, float y) {
        this.mapCoordinates.x = x;
        this.mapCoordinates.y = y;
    }
}
