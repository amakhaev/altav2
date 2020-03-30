package com.alta_v2.game.dao.facade.definition.mapper;

import com.alta_v2.game.dao.data.person.PersonEntity;
import com.alta_v2.model.NpcDefinitionModel;
import com.alta_v2.model.PlayerDefinitionModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mappings({
            @Mapping(source = "personId", target = "id"),
            @Mapping(target = "texturePath", qualifiedByName = "texturePath"),
            @Mapping(source = "x", target = "x"),
            @Mapping(source = "y", target = "y"),
    })
    PlayerDefinitionModel entityToPlayerDefinition(PersonEntity personEntity);

    @Mappings({
            @Mapping(source = "personId", target = "id"),
            @Mapping(source = "movementInterval", target = "repeatMovementInterval"),
            @Mapping(target = "texturePath", qualifiedByName = "texturePath"),
            @Mapping(source = "x", target = "x"),
            @Mapping(source = "y", target = "y"),
    })
    NpcDefinitionModel entityToNpcDefinition(PersonEntity personEntity);

    List<NpcDefinitionModel> entitiesToNpcDefinitions(List<PersonEntity> personEntity);

    @Named("texturePath")
    default String locationToLocationDto(PersonEntity personEntity) {
        return personEntity.getTextureFolder();
    }
}
