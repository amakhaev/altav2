package com.alta_v2.game.gamelogic.interaction

import com.alta_v2.game.gamelogic.data.map.MapModel
import com.alta_v2.game.gamelogic.data.npc.NpcModel

interface InteractionFactory {

    fun createInteractionManager(mapModel: MapModel, npcList: List<NpcModel>): InteractionManagerImpl

}