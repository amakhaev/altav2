package com.alta_v2.game.dao.provider

import com.google.inject.Provider
import com.j256.ormlite.jdbc.JdbcConnectionSource
import com.j256.ormlite.support.ConnectionSource
import lombok.extern.log4j.Log4j2
import mu.KotlinLogging
import java.sql.SQLException

class ConnectionSourceProvider : Provider<ConnectionSource?> {

    private val log = KotlinLogging.logger {  }

    override fun get(): ConnectionSource? {
        return try {
            JdbcConnectionSource("jdbc:sqlite:data.db3")
        } catch (e: SQLException) {
            log.error("Failed to create jdbc connection source", e)
            null
        }
    }
}