package com.alta_v2.rendering.tiledMapScreen;

import com.alta_v2.rendering.ScreenState;
import com.alta_v2.rendering.tiledMapScreen.state.NpcState;
import com.alta_v2.rendering.tiledMapScreen.state.PlayerState;
import com.badlogic.gdx.math.Vector2;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
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

    @AssistedInject
    public TiledMapState(@Assisted List<String> npcIds) {
        if (npcIds != null) {
            npcIds.forEach(npcId -> npcStateList.add(new NpcState(npcId)));
        }
    }

    public void updateMapCoordinates(float x, float y) {
        this.mapCoordinates.x = x;
        this.mapCoordinates.y = y;
    }
}
