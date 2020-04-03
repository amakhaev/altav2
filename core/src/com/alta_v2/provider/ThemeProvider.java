package com.alta_v2.provider;

import com.alta_v2.game.utils.Resources;
import com.alta_v2.rendering.config.Theme;
import com.google.inject.Provider;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;

public class ThemeProvider implements Provider<Theme> {
    @Override
    public Theme get() {
        Yaml yaml = new Yaml(new Constructor(Theme.class));
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(Resources.DARK_THEME);
        return yaml.load(inputStream);
    }
}
