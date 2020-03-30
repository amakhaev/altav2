package com.alta_v2.game.dao.provider;

import com.google.inject.Provider;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import lombok.extern.log4j.Log4j2;

import java.sql.SQLException;

@Log4j2
public class ConnectionSourceProvider implements Provider<ConnectionSource> {

    @Override
    public ConnectionSource get() {
        try {
            return new JdbcConnectionSource("jdbc:sqlite:data.db3");
        } catch (SQLException e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
