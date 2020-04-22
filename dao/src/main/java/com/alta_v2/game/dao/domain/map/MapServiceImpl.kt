package com.alta_v2.game.dao.domain.map

import com.alta_v2.game.dao.data.map.MapEntity
import com.google.inject.Inject
import com.j256.ormlite.dao.Dao
import com.j256.ormlite.dao.DaoManager
import com.j256.ormlite.support.ConnectionSource
import mu.KotlinLogging
import java.sql.SQLException

class MapServiceImpl @Inject constructor(connection: ConnectionSource) : MapService {

    private val log = KotlinLogging.logger {  }
    private lateinit var mapDao: Dao<MapEntity, Int>

    init {
        try {
            mapDao = DaoManager.createDao(connection, MapEntity::class.java)
        } catch (e: SQLException) {
            log.error("Failed to create mapDao", e)
        }
    }

    override fun getMapById(mapId: Int): MapEntity? {
        return try {
            val map = mapDao.queryForId(mapId)
            if (map != null) {
                log.debug("Map with internal description '{}' retrieved", map.internalDescription)
            }
            map
        } catch (e: SQLException) {
            log.error("Failed to get map with given mapId $mapId", e)
            null
        }
    }
}