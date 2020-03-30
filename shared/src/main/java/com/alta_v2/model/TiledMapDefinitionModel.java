package com.alta_v2.model;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class TiledMapDefinitionModel {

    private final String mapPath;
    private final PlayerDefinitionModel player;
    private final List<NpcDefinitionModel> npcList;

}
