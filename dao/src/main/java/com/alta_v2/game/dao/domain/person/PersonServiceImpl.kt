package com.alta_v2.game.dao.domain.person

import com.alta_v2.game.dao.data.person.PersonEntity
import com.alta_v2.game.dao.data.person.PersonType
import com.google.inject.Inject
import com.j256.ormlite.dao.Dao
import com.j256.ormlite.dao.DaoManager
import com.j256.ormlite.support.ConnectionSource
import mu.KotlinLogging
import java.sql.SQLException

class PersonServiceImpl @Inject constructor(connection: ConnectionSource) : PersonService {

    private val log = KotlinLogging.logger {  }
    private lateinit var personDao: Dao<PersonEntity, Int>

    init {
        try {
            personDao = DaoManager.createDao(connection, PersonEntity::class.java)
        } catch (e: SQLException) {
            log.error("Failed to create personDao", e)
        }
    }

    override fun getNpcForMap(mapId: Int): List<PersonEntity> {
        return try {
            personDao.queryBuilder().where()
                    .eq(PersonEntity.MAP_ID_COLUMN, mapId)
                    .and()
                    .eq(PersonEntity.TYPE_COLUMN, PersonType.NPC)
                    .query()
        } catch (e: SQLException) {
            log.error("Failed to get NPC for mapId $mapId", e)
            emptyList()
        }
    }

    override fun getPlayerForMap(mapId: Int): PersonEntity? {
        return try {
            personDao.queryBuilder().where()
                    .eq(PersonEntity.MAP_ID_COLUMN, mapId)
                    .and()
                    .eq(PersonEntity.TYPE_COLUMN, PersonType.PLAYER)
                    .queryForFirst()
        } catch (e: SQLException) {
            log.error("Failed to get Player for mapId $mapId", e)
            null
        }
    }
}