package com.alta_v2.game.dao.data.effect

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = "ui_effect_aggregations")
class EffectAggregationEntity {

    companion object {
        const val GROUP_ID_COLUMN = "group_id"
        const val ORDER_COLUMN = "group_order"
    }

    @DatabaseField(id = true, columnName = "effect_id")
    var effectId: Int = 0

    @DatabaseField(columnName = GROUP_ID_COLUMN)
    var groupId: Int = 0

    @DatabaseField(columnName = ORDER_COLUMN)
    var groupOrder: Int = 0

    @DatabaseField(columnName = "target_id")
    var targetId: Int? = null

    @DatabaseField(columnName = "dialog_id")
    var dialogId: Int? = null

    @DatabaseField(columnName = "change_texture_folder")
    var changeTextureFolder: String? = null

    @DatabaseField(columnName = "change_texture_name")
    var changeTextureName: String? = null

    @DatabaseField(columnName = "movement_x")
    var movementX: Int? = null

    @DatabaseField(columnName = "movement_y")
    var movementY: Int? = null

    @DatabaseField(columnName = "dialog_section_group_id")
    var dialogSectionGroupId: Int? = null

    val dialogSections: MutableList<DialogSectionEntity> = ArrayList()

    val texturePath: String?
        get() = changeTextureFolder + changeTextureName
}