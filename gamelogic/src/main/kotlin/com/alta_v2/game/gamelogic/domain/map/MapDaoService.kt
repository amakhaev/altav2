package com.alta_v2.game.gamelogic.domain.map

import com.alta_v2.model.TiledMapDefinitionModel

/**
 * Provides service to communicate with DAO layer.
 */
interface MapDaoService {

    /**
     * Gets the tiled map definition.
     */
    fun getDefinition(mapId: Int): TiledMapDefinitionModel?

}