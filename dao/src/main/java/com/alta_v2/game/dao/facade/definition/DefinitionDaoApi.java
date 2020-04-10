package com.alta_v2.game.dao.facade.definition;

import com.alta_v2.model.TiledMapDefinitionModel;

/**
 * Provides the facade API for working with definitions of map.
 */
public interface DefinitionDaoApi {

    /**
     * Gets the map definition with given map id.
     *
     * @param mapId - the ID of map to be used for search.
     * @return the {@link TiledMapDefinitionModel} instance.
     */
    TiledMapDefinitionModel getMapDefinition(int mapId);

}
