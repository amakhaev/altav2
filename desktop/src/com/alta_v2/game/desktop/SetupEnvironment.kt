package com.alta_v2.game.desktop

import com.alta_v2.DB_PATH
import com.xenomachina.argparser.ArgParser
import mu.KotlinLogging
import java.lang.Exception

private val log = KotlinLogging.logger {  }

fun setupEnvironment(appArgs: Array<String>) {
    try {
        ArgParser(appArgs).parseInto(::AppArgument).run {
            System.setProperty(DB_PATH, dbPath)

            log.info("Database URL: $dbPath")
        }
    } catch (e: Exception) {
        log.error("Failed to setup environment", e)
    }
}