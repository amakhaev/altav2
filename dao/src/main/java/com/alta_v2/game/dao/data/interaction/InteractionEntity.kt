package com.alta_v2.game.dao.data.interaction

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = "interactions")
class InteractionEntity {

    @DatabaseField(id = true, uniqueCombo = true)
    var id: Int = 0

    @DatabaseField(columnName = "effect_group_id")
    var effectGroupId: Int? = null

    @DatabaseField(columnName = "result_group_id")
    var resultGroupId: Int? = null
}