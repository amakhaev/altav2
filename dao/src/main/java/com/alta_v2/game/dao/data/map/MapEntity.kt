package com.alta_v2.game.dao.data.map

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = "maps")
class MapEntity {

    @DatabaseField(id = true)
    var id: Int = 0

    @DatabaseField
    lateinit var folder: String

    @DatabaseField
    lateinit var name: String

    @DatabaseField(columnName = "display_name")
    lateinit var displayName: String

    @DatabaseField(columnName = "internal_description")
    lateinit var internalDescription: String

    val mapPath: String
        get() = folder + name
}