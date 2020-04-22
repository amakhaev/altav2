package com.alta_v2.game.gamelogic.domain.map

import com.alta_v2.game.dao.facade.definition.DefinitionDaoApi
import com.google.inject.Inject

class MapDaoServiceImpl @Inject constructor(private val definitionDaoApi: DefinitionDaoApi) : MapDaoService {

    override fun getDefinition(mapId: Int) = definitionDaoApi.getMapDefinition(mapId)
}