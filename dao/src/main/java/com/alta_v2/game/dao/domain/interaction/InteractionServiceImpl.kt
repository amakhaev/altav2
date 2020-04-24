package com.alta_v2.game.dao.domain.interaction

import com.alta_v2.game.dao.data.interaction.InteractionGroupEntity
import com.alta_v2.game.dao.data.interaction.InteractionGroupEntity.Companion.ID_COLUMN
import com.alta_v2.game.dao.data.interaction.InteractionGroupEntity.Companion.ORDER_COLUMN
import com.google.inject.Inject
import com.j256.ormlite.dao.Dao
import com.j256.ormlite.dao.DaoManager
import com.j256.ormlite.support.ConnectionSource
import mu.KotlinLogging
import java.sql.SQLException

class InteractionServiceImpl @Inject constructor(connection: ConnectionSource) : InteractionService {

    private val log = KotlinLogging.logger {  }
    private lateinit var interactionGroupDao: Dao<InteractionGroupEntity, Int>

    init {
        try {
            interactionGroupDao = DaoManager.createDao(connection, InteractionGroupEntity::class.java)
        } catch (e: SQLException) {
            log.error("Failed to create interactionGroupDao", e)
        }
    }

    override fun getGroupsById(groupId: Int): List<InteractionGroupEntity> =
            try {
                interactionGroupDao.queryBuilder().orderBy(ORDER_COLUMN, true).where().eq(ID_COLUMN, groupId).query()
            } catch (e: SQLException) {
                log.error("Failed to get group of interactions with given groupId $groupId", e)
                emptyList()
            }

}