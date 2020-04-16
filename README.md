# AlTa-v2

### Prepare database
1. `sqlite3` should be installed before data migration process
2. Execute command `cd scripts; db_install.sh`. It will create database in the root folder.

### How to build
1. Just execute `gradlew desktop:dist`. It will create `jar` file in the `/desktop/build/libs`
2. Navigate to `/desktop/build/libs` and run `java -jar desktop-1.0.jar`

#### Application arguments
1. `dbPath` - full path to SQLite database, default `./data.db3`