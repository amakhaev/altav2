package com.alta_v2.provider

import com.alta_v2.game.utils.Resources
import com.alta_v2.rendering.config.Theme
import com.google.inject.Provider
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor

class ThemeProvider : Provider<Theme> {
    override fun get(): Theme {
        val yaml = Yaml()
        val inputStream = this.javaClass
                .classLoader
                .getResourceAsStream(Resources.DARK_THEME)
        return yaml.loadAs(inputStream, Theme::class.java)
    }
}