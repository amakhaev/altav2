package com.alta_v2.provider;

import com.alta_v2.game.utils.Resources;
import com.alta_v2.rendering.component.ComponentStyle;
import com.google.inject.Provider;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;

public class ComponentStyleProvider implements Provider<ComponentStyle> {

    @Override
    public ComponentStyle get() {
        Yaml yaml = new Yaml(new Constructor(ComponentStyle.class));
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(Resources.COMPONENT_STYLES);
        return yaml.load(inputStream);
    }
}
