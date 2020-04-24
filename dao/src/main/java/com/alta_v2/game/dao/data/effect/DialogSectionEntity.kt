package com.alta_v2.game.dao.data.effect

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = "dialog_sections")
class DialogSectionEntity {

    companion object {
        const val ID_COLUMN = "id"
        const val SECTIONS_COLUMN = "sections_order"
    }

    @DatabaseField(id = true, columnName = ID_COLUMN)
    var id: Int = 0

    @DatabaseField(columnName = "text")
    lateinit var text: String

    @DatabaseField(columnName = SECTIONS_COLUMN)
    var sectionsOrder: Int = 0
}