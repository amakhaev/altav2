package com.alta_v2.model;

import lombok.Builder;
import lombok.Value;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Value
@Builder
public class TiledMapDefinitionModel {

    private final String mapPath;
    private final String displayName;
    private final PlayerDefinitionModel player;
    private final List<NpcDefinitionModel> npcList;

    public List<Integer> getNpcIds() {
        if (npcList == null) {
            return Collections.emptyList();
        }

        return npcList.stream().map(ActorDefinitionModel::getId).collect(Collectors.toList());
    }

    public Map<Integer, String> getNpcPaths() {
        if (npcList == null) {
            return Collections.emptyMap();
        }

        return npcList.stream().collect(Collectors.toMap(ActorDefinitionModel::getId, ActorDefinitionModel::getTexturePath));
    }
}
