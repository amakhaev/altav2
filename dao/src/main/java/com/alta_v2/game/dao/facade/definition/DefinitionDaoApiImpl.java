package com.alta_v2.game.dao.facade.definition;

import com.alta_v2.aop.executionTime.PrintExecutionTime;
import com.alta_v2.game.dao.data.map.MapEntity;
import com.alta_v2.game.dao.domain.map.MapService;
import com.alta_v2.game.dao.domain.person.PersonService;
import com.alta_v2.game.dao.facade.definition.mapper.PersonMapper;
import com.alta_v2.model.TiledMapDefinitionModel;
import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class DefinitionDaoApiImpl implements DefinitionDaoApi {

    private final MapService mapService;
    private final PersonService personService;
    private final PersonMapper personMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    @PrintExecutionTime
    public TiledMapDefinitionModel getMapDefinition(int mapId) {
        MapEntity mapEntity = mapService.getMapById(mapId);
        if (mapEntity == null) {
            log.error("Map with given id {} not found", mapId);
            return null;
        }

        return TiledMapDefinitionModel.builder()
                .mapPath(mapEntity.getMapPath())
                .displayName(mapEntity.getDisplayName())
                .player(personMapper.entityToPlayerDefinition(personService.getPlayerForMap(mapId)))
                .npcList(personMapper.entitiesToNpcDefinitions(personService.getNpcForMap(mapId)))
                .build();
    }
}
