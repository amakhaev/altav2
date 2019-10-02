package com.alta_v2.game;

import com.alta_v2.game.actor.ActorFactory;
import com.alta_v2.game.configuration.AssetManagerProvider;
import com.alta_v2.game.controller.*;
import com.alta_v2.game.inputProcessor.InputProcessorFactory;
import com.alta_v2.game.screen.ScreenFactory;
import com.alta_v2.game.screen.TiledMapScreen;
import com.alta_v2.game.screen.TiledMapScreenImpl;
import com.badlogic.gdx.assets.AssetManager;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class CoreInjector extends AbstractModule {

    @Override
    protected void configure() {
        // Factories level
        install(new FactoryModuleBuilder().build(ScreenFactory.class));
        install(new FactoryModuleBuilder().build(InputProcessorFactory.class));
        install(new FactoryModuleBuilder().build(ActorFactory.class));

        // Common types level.
        bind(AssetManager.class).toProvider(AssetManagerProvider.class).in(Singleton.class);
        bind(GameManager.class).in(Singleton.class);

        // Controller level
        bind(InputController.class).to(InputControllerImpl.class);
        bind(TiledMapController.class).to(TiledMapControllerImpl.class);
        bind(ScreenController.class).to(ScreenControllerImpl.class);
    }

}
