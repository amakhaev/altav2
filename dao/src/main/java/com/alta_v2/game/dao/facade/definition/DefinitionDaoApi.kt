package com.alta_v2.game.dao.facade.definition

import com.alta_v2.model.InteractionDefinitionModel
import com.alta_v2.model.TiledMapDefinitionModel

/**
 * Provides the facade API for working with definitions of map.
 */
interface DefinitionDaoApi {

    /**
     * Gets the map definition with given map id.
     *
     * @param mapId - the ID of map to be used for search.
     * @return the [TiledMapDefinitionModel] instance.
     */
    fun getMapDefinition(mapId: Int): TiledMapDefinitionModel?

    /**
     * Gets the list of interaction definitions by given ID of group.
     */
    fun getInteractionDefinitions(interactionGroupId: Int): List<InteractionDefinitionModel>
}