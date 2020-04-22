#!/bin/bash

DATA_DB=data.db3
DATA_DB_FULL_PATH="../$DATA_DB"

rm -f "$DATA_DB_FULL_PATH"
touch $DATA_DB_FULL_PATH
chmod 777 $DATA_DB_FULL_PATH

sqlite3 $DATA_DB_FULL_PATH '.read ./data_db/schema.sql'
sqlite3 $DATA_DB_FULL_PATH '.read ./data_db/maps_data.sql'
sqlite3 $DATA_DB_FULL_PATH '.read ./data_db/textures_data.sql'
sqlite3 $DATA_DB_FULL_PATH '.read ./data_db/coordinates_data.sql'
sqlite3 $DATA_DB_FULL_PATH '.read ./data_db/map_objects_data.sql'
sqlite3 $DATA_DB_FULL_PATH '.read ./data_db/persons_data.sql'
sqlite3 $DATA_DB_FULL_PATH '.read ./data_db/map_to_objects_data.sql'
sqlite3 $DATA_DB_FULL_PATH '.read ./data_db/map_to_object_rules_data.sql'
sqlite3 $DATA_DB_FULL_PATH '.read ./data_db/dialog_parts_data.sql'
sqlite3 $DATA_DB_FULL_PATH '.read ./data_db/dialogs_data.sql'
sqlite3 $DATA_DB_FULL_PATH '.read ./data_db/ui_effects_data.sql'
sqlite3 $DATA_DB_FULL_PATH '.read ./data_db/effect_groups_data.sql'
sqlite3 $DATA_DB_FULL_PATH '.read ./data_db/interactions_data.sql'
sqlite3 $DATA_DB_FULL_PATH '.read ./data_db/interaction_groups_data.sql'
echo "Database $DATA_DB migrated successfully"