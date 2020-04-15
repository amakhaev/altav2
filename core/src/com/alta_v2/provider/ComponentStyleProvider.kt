package com.alta_v2.provider

import com.alta_v2.game.utils.Resources
import com.alta_v2.rendering.common.component.ComponentStyle
import com.google.inject.Provider
import org.yaml.snakeyaml.Yaml

class ComponentStyleProvider : Provider<ComponentStyle> {
    override fun get(): ComponentStyle {
        val yaml = Yaml()
        val inputStream = this.javaClass
                .classLoader
                .getResourceAsStream(Resources.COMPONENT_STYLES)
        return yaml.loadAs(inputStream, ComponentStyle::class.java)
    }
}