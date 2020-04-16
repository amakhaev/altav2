package com.alta_v2.game.dao.facade.definition

import com.alta_v2.aop.executionTime.PrintExecutionTime
import com.alta_v2.game.dao.domain.map.MapService
import com.alta_v2.game.dao.domain.person.PersonService
import com.alta_v2.model.TiledMapDefinitionModel
import com.google.inject.Inject
import mu.KotlinLogging
import java.lang.RuntimeException

open class DefinitionDaoApiImpl @Inject constructor(private val mapService: MapService,
                                                    private val personService: PersonService) : DefinitionDaoApi {

    private val log = KotlinLogging.logger {  }

    /**
     * {@inheritDoc}
     */
    @PrintExecutionTime
    override fun getMapDefinition(mapId: Int): TiledMapDefinitionModel? {
        val mapEntity = mapService.getMapById(mapId)
        if (mapEntity == null) {
            log.error("Map with given id {} not found", mapId)
            return null
        }

        val player = personService.getPlayerForMap(mapId) ?: throw RuntimeException("Player for give map does't exists")

        return TiledMapDefinitionModel(
                mapPath = mapEntity.mapPath,
                displayName = mapEntity.displayName,
                player = player.toPlayerDefinition(),
                npcList = convertToNpcDefinitions(personService.getNpcForMap(mapId))
        )
    }
}