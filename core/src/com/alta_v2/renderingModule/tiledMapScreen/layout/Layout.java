package com.alta_v2.renderingModule.tiledMapScreen.layout;

import com.alta_v2.renderingModule.Renderer;
import com.alta_v2.renderingModule.ScreenState;
import com.alta_v2.renderingModule.tiledMapScreen.TiledMapState;

/**
 * Provides the renderable layout.
 */
public interface Layout extends Renderer {

    default int defaultTileWidth() {
        return 32;
    }

    default int defaultTileHeight() {
        return 32;
    }

    default TiledMapState resolveClass(ScreenState screenState) {
        try {
            return ScreenState.resolveState(screenState, TiledMapState.class);
        } catch (ClassCastException e) {
            e.printStackTrace();
            return null;
        }
    }
}
