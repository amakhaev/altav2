CREATE TABLE textures (
    id INTEGER PRIMARY KEY NOT NULL CHECK(id>0 AND id<=500),
    folder VARCHAR(512) NOT NULL,
    name VARCHAR(512) NOT NULL,
    internal_description TEXT NOT NULL,
    UNIQUE(folder, name)
);

CREATE TABLE coordinates (
    id INTEGER PRIMARY KEY NOT NULL CHECK(id>500 AND id<=1000),
    x INTEGER NOT NULL,
    y INTEGER NOT NULL
);

CREATE TABLE maps (
    id INTEGER PRIMARY KEY NOT NULL CHECK(id>1000 AND id<=1500),
    folder VARCHAR(512) NOT NULL,
    name VARCHAR(512) NOT NULL,
    display_name VARCHAR(256) NOT NULL,
    internal_description TEXT NOT NULL,
    UNIQUE(folder, name)
);

CREATE TABLE map_objects (
    id INTEGER PRIMARY KEY NOT NULL CHECK(id>1500 AND id<=3000),
    texture_id INTEGER NOT NULL,
    FOREIGN KEY(texture_id) REFERENCES textures(id)
);

CREATE TABLE persons (
    id INTEGER PRIMARY KEY NOT NULL,
    type VARCHAR(16) NOT NULL CHECK(type IN ('PLAYER', 'NPC')),
    repeatable_movement_interval INTEGER,
    internal_description TEXT NOT NULL,
    FOREIGN KEY(id) REFERENCES map_objects(id)
);

CREATE TABLE static_objects (
    id INTEGER PRIMARY KEY NOT NULL,
    internal_description TEXT NOT NULL,
    FOREIGN KEY(id) REFERENCES map_objects(id)
);

CREATE TABLE map_to_objects (
    id INTEGER PRIMARY KEY NOT NULL CHECK(id>3000 AND id<=3500),
    map_object_id INTEGER NOT NULL,
    map_id INTEGER NOT NULL,
    coordinate_id INTEGER NOT NULL,
    FOREIGN KEY(map_object_id) REFERENCES map_objects(id),
    FOREIGN KEY(map_id) REFERENCES maps(id),
    FOREIGN KEY(coordinate_id) REFERENCES coordinates(id)
);

CREATE TABLE dialog_sections (
    id INTEGER NOT NULL CHECK(id>3500 AND id<=6000),
    text TEXT NOT NULL,
    sections_order INTEGER NOT NULL,
    PRIMARY KEY (id, sections_order)
);

CREATE TABLE dialogs (
    id INTEGER PRIMARY KEY NOT NULL CHECK(id>6000 AND id<=7000),
    section_id INTEGER NOT NULL,
    UNIQUE (id, section_id),
    FOREIGN KEY(section_id) REFERENCES dialog_sections(id)
);

CREATE TABLE change_textures (
    id INTEGER PRIMARY KEY NOT NULL CHECK(id>7000 AND id<=7500),
    texture_to_id INTEGER NOT NULL,
    FOREIGN KEY(texture_to_id) REFERENCES textures(id)
);

CREATE TABLE movements (
    id INTEGER PRIMARY KEY NOT NULL CHECK(id>7500 AND id<=8000),
    target_coordinate_id INTEGER NOT NULL,
    FOREIGN KEY(target_coordinate_id) REFERENCES coordinates(id)
);

CREATE TABLE ui_effects (
    id INTEGER PRIMARY KEY NOT NULL CHECK(id>8000 AND id<=10000),
    change_textures_id INTEGER,
    dialog_id INTEGER,
    movement_id INTEGER,
    target_object_id INTEGER,
    internal_description TEXT NOT NULL,
    FOREIGN KEY(change_textures_id) REFERENCES change_textures(id),
    FOREIGN KEY(dialog_id) REFERENCES dialogs(id),
    FOREIGN KEY(movement_id) REFERENCES movements(id),
    FOREIGN KEY(target_object_id) REFERENCES map_objects(id)
);

CREATE TABLE removed_objects (
    id INTEGER PRIMARY KEY NOT NULL CHECK(id>10000 AND id<=10500),
    map_to_object_id INTEGER NOT NULL,
    FOREIGN KEY(map_to_object_id) REFERENCES map_to_objects(id)
);

CREATE TABLE added_objects (
    id INTEGER PRIMARY KEY NOT NULL CHECK(id>10500 AND id<=11000),
    map_to_object_id INTEGER NOT NULL,
    FOREIGN KEY(map_to_object_id) REFERENCES map_to_objects(id)
);

CREATE TABLE updated_coordinates (
    id INTEGER PRIMARY KEY NOT NULL CHECK(id>11000 AND id<=11500),
    map_to_object_id INTEGER NOT NULL,
    coordinate_id INTEGER NOT NULL,
    FOREIGN KEY(map_to_object_id) REFERENCES map_to_objects(id),
    FOREIGN KEY(coordinate_id) REFERENCES coordinates(id)
);

CREATE TABLE updated_textures (
    id INTEGER PRIMARY KEY NOT NULL CHECK(id>11500 AND id<=13000),
    map_to_object_id INTEGER NOT NULL,
    texture_id INTEGER NOT NULL,
    FOREIGN KEY(map_to_object_id) REFERENCES map_to_objects(id),
    FOREIGN KEY(texture_id) REFERENCES textures(id)
);

CREATE TABLE results (
    id INTEGER PRIMARY KEY NOT NULL CHECK(id>13000 AND id<=14000),
    removed_object_id INTEGER,
    added_object_id INTEGER,
    updated_coordinate_id INTEGER,
    updated_texture_id INTEGER,
    internal_description TEXT NOT NULL,
    FOREIGN KEY(removed_object_id) REFERENCES removed_objects(id),
    FOREIGN KEY(added_object_id) REFERENCES added_objects(id),
    FOREIGN KEY(updated_coordinate_id) REFERENCES updated_coordinates(id),
    FOREIGN KEY(updated_texture_id) REFERENCES updated_textures(id)
);

CREATE TABLE effect_groups (
    id INTEGER NOT NULL CHECK(id>14000 AND id<=14500),
    ui_effect_id INTEGER NOT NULL,
    effect_order INTEGER NOT NULL,
    PRIMARY KEY (id, ui_effect_id),
    UNIQUE (id, ui_effect_id, effect_order),
    FOREIGN KEY(ui_effect_id) REFERENCES ui_effects(id)
);

CREATE TABLE result_groups (
    id INTEGER PRIMARY KEY NOT NULL CHECK(id>14500 AND id<=15000),
    group_id INTEGER NOT NULL,
    result_id INTEGER NOT NULL,
    FOREIGN KEY(result_id) REFERENCES results(id)
);

CREATE TABLE quests (
    id INTEGER PRIMARY KEY NOT NULL CHECK(id>18000 AND id<=18500),
    display_name VARCHAR(512) NOT NULL,
    internal_description TEXT NOT NULL
);

CREATE TABLE interactions (
    id INTEGER PRIMARY KEY NOT NULL CHECK(id>16000 AND id<=17000),
    effect_group_id INTEGER NOT NULL,
    result_group_id INTEGER,
    internal_description TEXT NOT NULL,
    FOREIGN KEY(effect_group_id) REFERENCES effect_groups(id),
    FOREIGN KEY(result_group_id) REFERENCES result_groups(id)
);

CREATE TABLE interaction_groups (
    id INTEGER NOT NULL CHECK(id>17500 AND id<=18000),
    interaction_id INTEGER NOT NULL,
    interaction_order INTEGER NOT NULL,
    PRIMARY KEY (id, interaction_id),
    FOREIGN KEY(interaction_id) REFERENCES interactions(id)
);

CREATE TABLE checkpoints (
    id INTEGER PRIMARY KEY NOT NULL CHECK(id>15000 AND id<=16000),
    quest_id INTEGER NOT NULL,
    effect_group_id INTEGER,
    result_group_id INTEGER,
    checkpoint_order INTEGER NOT NULL,
    initial_interaction_id INTEGER NOT NULL,
    internal_description TEXT NOT NULL,
    FOREIGN KEY(quest_id) REFERENCES quests(id),
    FOREIGN KEY(effect_group_id) REFERENCES effect_groups(id),
    FOREIGN KEY(result_group_id) REFERENCES result_groups(id),
    FOREIGN KEY(initial_interaction_id) REFERENCES interactions(id)
);

CREATE TABLE quest_initiation_rules (
    id INTEGER PRIMARY KEY NOT NULL CHECK(id>18500 AND id<=19000),
    quest_id INTEGER NOT NULL,
    completed_quest_id INTEGER,
    completed_checkpoint_id INTEGER,
    map_to_object_id INTEGER NOT NULL,
    interaction_id INTEGER NOT NULL,
    FOREIGN KEY(quest_id) REFERENCES quests(id),
    FOREIGN KEY(completed_quest_id) REFERENCES quests(id),
    FOREIGN KEY(completed_checkpoint_id) REFERENCES checkpoints(id),
    FOREIGN KEY(map_to_object_id) REFERENCES map_to_objects(id),
    FOREIGN KEY(interaction_id) REFERENCES interactions(id)
);

CREATE TABLE map_to_object_rules (
    id INTEGER PRIMARY KEY NOT NULL CHECK(id>19000 AND id<=20000),
    active_quest_id INTEGER,
    completed_quest_id INTEGER,
    active_checkpoint_id INTEGER,
    completed_checkpoint_id INTEGER,
    map_to_object_id INTEGER NOT NULL,
    interaction_group_id INTEGER,
    FOREIGN KEY(active_quest_id) REFERENCES quests(id),
    FOREIGN KEY(completed_quest_id) REFERENCES quests(id),
    FOREIGN KEY(active_checkpoint_id) REFERENCES checkpoints(id),
    FOREIGN KEY(completed_checkpoint_id) REFERENCES checkpoints(id),
    FOREIGN KEY(map_to_object_id) REFERENCES map_to_objects(id),
    FOREIGN KEY(interaction_group_id) REFERENCES interaction_groups(id)
);

CREATE TABLE users (
    id INTEGER PRIMARY KEY NOT NULL
);

CREATE TABLE interaction_group_statuses (
    interaction_group_id INTEGER PRIMARY KEY NOT NULL,
    status VARCHAR(128) NOT NULL,
    user_id INTEGER NOT NULL,
    FOREIGN KEY(interaction_group_id) REFERENCES interaction_groups(group_id)
    FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE TABLE checkpoint_statuses (
    checkpoint_id INTEGER PRIMARY KEY NOT NULL,
    status VARCHAR(128) NOT NULL,
    user_id INTEGER NOT NULL,
    FOREIGN KEY(checkpoint_id) REFERENCES checkpoints(id)
    FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE TABLE quest_statuses (
    quest_id INTEGER PRIMARY KEY NOT NULL,
    status VARCHAR(128) NOT NULL,
    user_id INTEGER NOT NULL,
    FOREIGN KEY(quest_id) REFERENCES quests(id)
    FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE TABLE result_group_statuses (
    result_group_statuse_id INTEGER PRIMARY KEY NOT NULL,
    user_id INTEGER NOT NULL,
    FOREIGN KEY(result_group_statuse_id) REFERENCES result_group_statuses(group_id)
    FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE VIEW actual_person_definitions AS
SELECT
    mto.map_id                      AS map_id,
    mto.map_object_id               AS person_id,
    t.folder                        AS texture_folder,
    t.name                          AS texture_name,
    p.type                          AS type,
    p.repeatable_movement_interval  AS movement_interval,
    c.x                             AS x,
    c.y                             AS y,
    mtor.interaction_group_id       AS interaction_group_id
FROM map_to_object_rules mtor
    INNER JOIN map_to_objects mto ON mtor.map_to_object_id=mto.id
    INNER JOIN map_objects mo ON mto.map_object_id=mo.id
    INNER JOIN persons p ON mto.map_object_id=p.id
    INNER JOIN coordinates c ON mto.coordinate_id=c.id
    INNER JOIN textures t ON mo.texture_id=t.id;

CREATE VIEW ui_effect_aggregations AS
SELECT
    eg.id AS group_id,
    uief.id AS effect_id,
    eg.effect_order AS group_order,
    uief.target_object_id AS target_id,
    uief.dialog_id AS dialog_id,
    t.folder AS change_texture_folder,
    t.name AS change_texture_name,
    crd.x AS movement_x,
    crd.y AS movement_y,
    dlg.section_id AS dialog_section_group_id
FROM effect_groups eg
    JOIN ui_effects uief ON eg.ui_effect_id=uief.id
    LEFT JOIN change_textures ct ON uief.change_textures_id=ct.id
    LEFT JOIN textures t ON ct.texture_to_id=t.id
    LEFT JOIN movements mov ON uief.movement_id=mov.id
    LEFT JOIN coordinates crd ON mov.target_coordinate_id=crd.id
    LEFT JOIN dialogs dlg ON uief.dialog_id=dlg.id