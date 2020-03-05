package com.alta_v2.rendering.tiledMapScreen;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

/**
 * Provides the metadata related to tiled map.
 */
@Getter
@RequiredArgsConstructor
public final class TiledMapMetadata {

    private final String mapPath;
    private final String actorTexturePath;
    private final Map<String, String> npcTextures;

}
