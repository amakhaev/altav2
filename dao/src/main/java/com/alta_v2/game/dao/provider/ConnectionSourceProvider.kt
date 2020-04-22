package com.alta_v2.game.dao.provider

import com.alta_v2.DB_PATH
import com.google.inject.Provider
import com.j256.ormlite.jdbc.JdbcConnectionSource
import com.j256.ormlite.support.ConnectionSource
import mu.KotlinLogging
import java.sql.SQLException

class ConnectionSourceProvider : Provider<ConnectionSource?> {

    private val log = KotlinLogging.logger {  }

    override fun get(): ConnectionSource? {
        return try {
            val dbPath = System.getProperty(DB_PATH)

            return if (dbPath != null) {
                JdbcConnectionSource("jdbc:sqlite:$dbPath")
            } else {
                JdbcConnectionSource("jdbc:sqlite:data.db3")
            }
        } catch (e: SQLException) {
            log.error("Failed to create jdbc connection source", e)
            null
        }
    }
}