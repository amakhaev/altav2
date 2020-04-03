package com.alta_v2.provider;

import com.alta_v2.game.utils.Resources;
import com.alta_v2.rendering.config.AppConfig;
import com.google.inject.Provider;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;

public class AppConfigProvider implements Provider<AppConfig> {
    @Override
    public AppConfig get() {
        Yaml yaml = new Yaml(new Constructor(AppConfig.class));
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(Resources.APP_CONFIG);
        return yaml.load(inputStream);
    }
}
