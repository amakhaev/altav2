package com.alta_v2.game.dao.data.map;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;

@Getter
@DatabaseTable(tableName = "maps")
public class MapEntity {

    @DatabaseField(id = true)
    private int id;

    @DatabaseField
    private String folder;

    @DatabaseField
    private String name;

    @DatabaseField(columnName = "display_name")
    private String displayName;

    @DatabaseField(columnName = "internal_description")
    private String internalDescription;

    public String getMapPath() {
        return this.folder + this.name;
    }
}
