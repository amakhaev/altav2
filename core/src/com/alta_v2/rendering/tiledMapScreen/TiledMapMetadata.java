package com.alta_v2.rendering.tiledMapScreen;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Provides the metadata related to tiled map.
 */
@Getter
@RequiredArgsConstructor
public final class TiledMapMetadata {

    private final String mapPath;
    private final String actorTexturePath;
    private final Map<Integer, String> npcTextures;

    public List<String> getActorTextures() {
        List<String> textures = Lists.newArrayList();

        if (!Strings.isNullOrEmpty(actorTexturePath)) {
            textures.add(actorTexturePath);
        }

        if (npcTextures != null) {
            textures.addAll(npcTextures.values());
        }

        return textures;
    }

}
