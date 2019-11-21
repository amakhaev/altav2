package com.alta_v2.renderingModule.tiledMapScreen;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Provides the metadata related to tiled map.
 */
@Getter
@RequiredArgsConstructor
public final class TiledMapMetadata {

    private final String mapPath;
    private final String actorTexturePath;

}
