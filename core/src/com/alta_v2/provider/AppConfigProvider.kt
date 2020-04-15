package com.alta_v2.provider

import com.alta_v2.game.utils.Resources
import com.alta_v2.rendering.config.AppConfig
import com.google.inject.Provider
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor

class AppConfigProvider : Provider<AppConfig> {

    override fun get(): AppConfig {
        val yaml = Yaml()
        val inputStream = this.javaClass
                .classLoader
                .getResourceAsStream(Resources.APP_CONFIG)
        return yaml.loadAs(inputStream, AppConfig::class.java)
    }
}