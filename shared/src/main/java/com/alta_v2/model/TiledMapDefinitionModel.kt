package com.alta_v2.model

import java.util.stream.Collectors

class TiledMapDefinitionModel(val mapPath: String,
                              val displayName: String,
                              val player: PlayerDefinitionModel,
                              val npcList: List<NpcDefinitionModel> = listOf()

) {
    val npcIds: List<Int>
        get() = npcList.stream().map(ActorDefinitionModel::id).collect(Collectors.toList())

    val npcPaths: Map<Int, String>
        get() = npcList.stream().collect(Collectors.toMap(ActorDefinitionModel::id, ActorDefinitionModel::texturePath))
}