package com.alta_v2.game.dao.facade.definition

import com.alta_v2.aop.executionTime.PrintExecutionTime
import com.alta_v2.game.dao.domain.effect.EffectService
import com.alta_v2.game.dao.domain.interaction.InteractionService
import com.alta_v2.game.dao.domain.map.MapService
import com.alta_v2.game.dao.domain.person.PersonService
import com.alta_v2.game.dao.facade.definition.extension.toDefinition
import com.alta_v2.game.dao.facade.definition.extension.toNpcDefinitions
import com.alta_v2.game.dao.facade.definition.extension.toPlayerDefinition
import com.alta_v2.game.dao.facade.definition.extension.toUiEffectDefinition
import com.alta_v2.model.InteractionDefinitionModel
import com.alta_v2.model.TiledMapDefinitionModel
import com.google.inject.Inject
import mu.KotlinLogging
import java.lang.RuntimeException

open class DefinitionDaoApiImpl @Inject constructor(private val mapService: MapService,
                                                    private val personService: PersonService,
                                                    private val interactionService: InteractionService,
                                                    private val effectService: EffectService) : DefinitionDaoApi {

    private val log = KotlinLogging.logger {  }

    @PrintExecutionTime(operationName = "Get map definition")
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
                npcList = personService.getNpcForMap(mapId).toNpcDefinitions()
        )
    }

    @PrintExecutionTime(operationName = "Get interaction definitions")
    override fun getInteractionDefinitions(interactionGroupId: Int): List<InteractionDefinitionModel> {
        val interactions = interactionService.getGroupsById(interactionGroupId)
        val effects = effectService.getEffects(interactions.mapNotNull { it.interaction.effectGroupId })

        return interactions.map { item ->
            val uiEffects = effects[item.interaction.effectGroupId]?.map { ef -> ef.toUiEffectDefinition() } ?: emptyList()
            item.interaction.toDefinition(uiEffects)
        }
    }

}