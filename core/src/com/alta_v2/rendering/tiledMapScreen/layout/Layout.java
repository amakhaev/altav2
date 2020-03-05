package com.alta_v2.rendering.tiledMapScreen.layout;

import com.alta_v2.rendering.Renderer;
import com.alta_v2.rendering.ScreenState;
import com.alta_v2.rendering.tiledMapScreen.TiledMapState;

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
