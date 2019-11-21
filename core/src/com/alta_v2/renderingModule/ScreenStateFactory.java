package com.alta_v2.renderingModule;

import com.alta_v2.renderingModule.menuScreen.MenuState;
import com.alta_v2.renderingModule.tiledMapScreen.TiledMapState;

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
    TiledMapState createTiledMapState();

}
