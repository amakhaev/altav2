package com.alta_v2.game.dao.domain.person;

import com.alta_v2.game.dao.data.person.PersonEntity;
import com.alta_v2.game.dao.data.person.PersonType;
import com.google.inject.Inject;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import lombok.extern.log4j.Log4j2;

import java.sql.SQLException;
import java.util.List;

@Log4j2
public class PersonServiceImpl implements PersonService {

    private Dao<PersonEntity, Integer> personDao;

    @Inject
    public PersonServiceImpl(ConnectionSource connection) {
        try {
            personDao = DaoManager.createDao(connection, PersonEntity.class);
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public List<PersonEntity> getNpcForMap(int mapId) {
        try {
            return personDao.queryBuilder().where()
                    .eq(PersonEntity.MAP_ID_COLUMN, mapId)
                    .and()
                    .eq(PersonEntity.TYPE_COLUMN, PersonType.NPC)
                    .query();
        } catch (SQLException e) {
            log.error(e);
            return null;
        }
    }

    @Override
    public PersonEntity getPlayerForMap(int mapId) {
        try {
            return personDao.queryBuilder().where()
                    .eq(PersonEntity.MAP_ID_COLUMN, mapId)
                    .and()
                    .eq(PersonEntity.TYPE_COLUMN, PersonType.PLAYER)
                    .queryForFirst();
        } catch (SQLException e) {
            log.error(e);
            return null;
        }
    }
}
