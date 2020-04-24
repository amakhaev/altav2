package com.alta_v2.game.dao.domain.effect

import com.alta_v2.game.dao.data.effect.DialogSectionEntity
import com.alta_v2.game.dao.data.effect.EffectAggregationEntity
import com.google.inject.Inject
import com.j256.ormlite.dao.Dao
import com.j256.ormlite.dao.DaoManager
import com.j256.ormlite.support.ConnectionSource
import mu.KotlinLogging
import java.sql.SQLException

class EffectServiceImpl @Inject constructor(connection: ConnectionSource) : EffectService {

    private val log = KotlinLogging.logger {  }
    private lateinit var effectAggregationDao: Dao<EffectAggregationEntity, Int>
    private lateinit var dialogSectionDao: Dao<DialogSectionEntity, Int>

    init {
        try {
            effectAggregationDao = DaoManager.createDao(connection, EffectAggregationEntity::class.java)
            dialogSectionDao = DaoManager.createDao(connection, DialogSectionEntity::class.java)
        } catch (e: SQLException) {
            log.error("Failed to create DAO", e)
        }
    }

    override fun getEffects(groupIds: List<Int>): Map<Int, List<EffectAggregationEntity>> =
        try {
            val effectAggregations = effectAggregationDao.queryBuilder()
                    .where()
                    .`in`(EffectAggregationEntity.GROUP_ID_COLUMN, groupIds)
                    .query()

            // add dialogs
            val sections = getDialogSections(effectAggregations.mapNotNull { it.dialogSectionGroupId })
            effectAggregations.forEach {
                it.dialogSections.addAll(sections.getOrDefault(it.dialogSectionGroupId, emptyList()))
            }

            // grouping and sorting
            val aggregationEffectMap = effectAggregations.groupBy { agrEffect -> agrEffect.groupId }
            aggregationEffectMap.forEach { (_, agrEffects) -> agrEffects.sortedBy { d -> d.groupOrder }}

            aggregationEffectMap
        } catch (e: SQLException) {
            log.error("Failed to get effects", e)
            emptyMap()
        }


    private fun getDialogSections(sectionIds: List<Int>): Map<Int, List<DialogSectionEntity>> =
            try {
                val sectionMap = dialogSectionDao.queryBuilder()
                        .where()
                        .`in`(DialogSectionEntity.ID_COLUMN, sectionIds)
                        .query()
                        .groupBy { it.id }

                sectionMap.forEach { (_, dialogs) -> dialogs.sortedBy { d -> d.sectionsOrder }}

                sectionMap
            } catch (e: SQLException) {
                log.error("Failed to get dialog effects", e)
                emptyMap()
            }

}