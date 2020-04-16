package com.alta_v2.game.desktop

import com.xenomachina.argparser.ArgParser

class AppArgument(parser: ArgParser) {

    val dbPath by parser.storing(
            "-D", "--dbPath",
            help = "Path to SQLite database"
    )

}