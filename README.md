# AlTa-v2

### Prepare database
1. `sqlite3` should ne installed before data migration process
2. Execute command `cd scripts; db_install.sh`

### How to build
1. Just execute `gradlew desktop:dist`. It will create `jar` file in the `/desktop/build/libs`
2. Navigate to `/desktop/build/libs` and run `java -jar desktop-1.0.jar`
