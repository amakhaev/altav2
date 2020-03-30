package com.alta_v2.game.dao.domain.person;

import com.alta_v2.game.dao.data.person.PersonEntity;

import java.util.List;

public interface PersonService {

    List<PersonEntity> getNpcForMap(int mapId);

    PersonEntity getPlayerForMap(int mapId);

}
