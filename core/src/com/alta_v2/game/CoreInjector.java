package com.alta_v2.game;

import com.alta_v2.game.controller.InputController;
import com.alta_v2.game.controller.InputControllerImpl;
import com.alta_v2.game.controller.TiledMapController;
import com.alta_v2.game.controller.TiledMapControllerImpl;
import com.alta_v2.game.inputProcessor.InputProcessorFactory;
import com.alta_v2.game.screen.ScreenFactory;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class CoreInjector extends AbstractModule {

    @Override
    protected void configure() {
        // Factories level
        install(new FactoryModuleBuilder().build(ScreenFactory.class));
        install(new FactoryModuleBuilder().build(InputProcessorFactory.class));

        bind(GameManager.class).in(Singleton.class);

        // Controller level
        bind(InputController.class).to(InputControllerImpl.class);
        bind(TiledMapController.class).to(TiledMapControllerImpl.class);
    }

}
