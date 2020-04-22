package com.alta_v2.game.dao.data.interaction

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = "interaction_groups")
class InteractionGroupEntity {

    companion object {
        const val ID_COLUMN = "id"
        const val ORDER_COLUMN = "interaction_order"
    }

    @DatabaseField(id = true, columnName = ID_COLUMN, uniqueCombo = true)
    var id: Int = 0

    @DatabaseField(columnName = "interaction_id", uniqueCombo = true, foreign = true, foreignAutoRefresh = true)
    lateinit var interaction: InteractionEntity

    @DatabaseField(columnName = ORDER_COLUMN)
    var order: Int = 0

}