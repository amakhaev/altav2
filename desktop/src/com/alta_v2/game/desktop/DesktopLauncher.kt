package com.alta_v2.game.desktop

import com.alta_v2.CoreInjector
import com.alta_v2.SharedInjector
import com.alta_v2.game.dao.DaoInjector
import com.alta_v2.game.gamelogic.GameLogic
import com.alta_v2.game.gamelogic.GameLogicInjector
import com.alta_v2.rendering.config.AppConfig
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import com.google.inject.Guice
import com.xenomachina.argparser.ArgParser

fun main(args: Array<String>) {
    setupEnvironment(args)

    val injector = Guice.createInjector(
            SharedInjector(), CoreInjector(), GameLogicInjector(), DaoInjector()
    )

    val appConfig = injector.getInstance(AppConfig::class.java)

    val config = Lwjgl3ApplicationConfiguration()
    config.setTitle(appConfig.title)
    config.setWindowedMode(appConfig.width, appConfig.height)
    config.setResizable(false)

    val altaV2 = injector.getInstance(GameLogic::class.java).altaV2
    Lwjgl3Application(altaV2, config)
}