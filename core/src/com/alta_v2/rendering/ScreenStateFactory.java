package com.alta_v2.rendering;

import com.alta_v2.rendering.menuScreen.MenuState;
import com.alta_v2.rendering.tiledMapScreen.TiledMapState;

import java.util.List;

/**
 * Provides the factory for state of screens.
 */
public interface ScreenStateFactory {

    /**
     * Creates the instance of {@link MenuState}.
     */
    MenuState createMenuState();

    /**
     * Creates the instance of {@link TiledMapState}.
     */
    TiledMapState createTiledMapState(List<Integer> npcIds);

}
