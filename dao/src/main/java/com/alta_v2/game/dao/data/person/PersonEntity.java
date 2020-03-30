package com.alta_v2.game.dao.data.person;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;

@Getter
@DatabaseTable(tableName = "actual_person_definitions")
public class PersonEntity {

    public final static String MAP_ID_COLUMN = "map_id";
    public final static String TYPE_COLUMN = "type";

    @DatabaseField(columnName = "person_id", id = true)
    private int personId;

    @DatabaseField(columnName = MAP_ID_COLUMN)
    private int mapId;

    @DatabaseField(columnName = "texture_folder")
    private String textureFolder;

    @DatabaseField(columnName = "texture_name")
    private String textureName;

    @DatabaseField(columnName = TYPE_COLUMN, dataType = DataType.ENUM_STRING)
    private PersonType type;

    @DatabaseField(columnName = "movement_interval")
    private Integer movementInterval;

    @DatabaseField(columnName = "x")
    private int x;

    @DatabaseField(columnName = "y")
    private int y;

    public String getTexturePath() {
        return this.textureFolder + this.textureName;
    }
}
