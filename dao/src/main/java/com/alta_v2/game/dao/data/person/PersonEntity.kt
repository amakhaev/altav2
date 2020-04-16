package com.alta_v2.game.dao.data.person

import com.j256.ormlite.field.DataType
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import lombok.Getter

@DatabaseTable(tableName = "actual_person_definitions")
class PersonEntity {

    companion object {
        const val MAP_ID_COLUMN = "map_id"
        const val TYPE_COLUMN = "type"
    }

    @DatabaseField(columnName = "person_id", id = true)
    var personId = 0

    @DatabaseField(columnName = MAP_ID_COLUMN)
    var mapId = 0

    @DatabaseField(columnName = "texture_folder")
    lateinit var textureFolder: String

    @DatabaseField(columnName = "texture_name")
    lateinit var textureName: String

    @DatabaseField(columnName = TYPE_COLUMN, dataType = DataType.ENUM_STRING)
    lateinit var type: PersonType

    @DatabaseField(columnName = "movement_interval")
    var movementInterval: Int = 0

    @DatabaseField(columnName = "x")
    var x = 0

    @DatabaseField(columnName = "y")
    var y = 0

    val texturePath: String
        get() = textureFolder + textureName

}