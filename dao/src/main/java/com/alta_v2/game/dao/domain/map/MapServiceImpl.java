package com.alta_v2.game.dao.domain.map;

import com.alta_v2.game.dao.data.map.MapEntity;
import com.google.inject.Inject;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import lombok.extern.log4j.Log4j2;

import java.sql.SQLException;

@Log4j2
public class MapServiceImpl implements MapService {

    private Dao<MapEntity, Integer> mapDao;

    @Inject
    public MapServiceImpl(ConnectionSource connection) {
        try {
            mapDao = DaoManager.createDao(connection, MapEntity.class);
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public MapEntity getMapById(int mapId) {
        try {
            MapEntity map = mapDao.queryForId(mapId);
            if (map != null) {
                log.debug("Map with internal description '{}' retrieved", map.getInternalDescription());
            }

            return map;
        } catch (SQLException e) {
            log.error(e);
            return null;
        }
    }
}
