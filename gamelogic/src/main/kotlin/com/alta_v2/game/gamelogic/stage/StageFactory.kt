package com.alta_v2.game.gamelogic.stage

import com.alta_v2.game.gamelogic.data.map.MapModel
import com.alta_v2.game.gamelogic.data.npc.NpcModel

interface StageFactory {

    fun createMenuStage(): MenuStage
    fun createMapStage(mapModel: MapModel, npcList: List<NpcModel>): MapStage

}