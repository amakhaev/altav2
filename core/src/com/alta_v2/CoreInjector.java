package com.alta_v2;

import com.alta_v2.aop.dynamicAssetLoader.DynamicAssetHandler;
import com.alta_v2.aop.dynamicAssetLoader.DynamicAssetLoader;
import com.alta_v2.facade.coreApi.CoreApi;
import com.alta_v2.facade.coreApi.CoreApiImpl;
import com.alta_v2.facade.tiledMapApi.TiledMapApi;
import com.alta_v2.facade.tiledMapApi.TiledMapApiImpl;
import com.alta_v2.game.ScreenManager;
import com.alta_v2.game.component.ComponentFactory;
import com.alta_v2.game.inputProcessor.InputProcessorFactory;
import com.alta_v2.game.screen.GameScreenFactory;
import com.alta_v2.mediatorModule.ProcessMediator;
import com.alta_v2.mediatorModule.ProcessMediatorImpl;
import com.alta_v2.mediatorModule.screen.context.ContextFactory;
import com.alta_v2.mediatorModule.screen.context.ContextFactoryImpl;
import com.alta_v2.mediatorModule.serde.UpdaterFactory;
import com.alta_v2.renderingModule.ScreenFactory;
import com.alta_v2.renderingModule.ScreenStateFactory;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.matcher.Matchers;

public class CoreInjector extends AbstractModule {

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder().build(InputProcessorFactory.class));
        install(new FactoryModuleBuilder().build(ComponentFactory.class));
        install(new FactoryModuleBuilder().build(ScreenFactory.class));
        install(new FactoryModuleBuilder().build(GameScreenFactory.class));
        install(new FactoryModuleBuilder().build(UpdaterFactory.class));
        install(new FactoryModuleBuilder().build(ScreenStateFactory.class));

        bind(ScreenManager.class).in(Singleton.class);

        bind(ContextFactory.class).to(ContextFactoryImpl.class).in(Singleton.class);
        bind(ProcessMediator.class).to(ProcessMediatorImpl.class).in(Singleton.class);

        // AOP
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(DynamicAssetLoader.class), new DynamicAssetHandler());

        // API
        bind(CoreApi.class).to(CoreApiImpl.class).in(Singleton.class);
        bind(TiledMapApi.class).to(TiledMapApiImpl.class).in(Singleton.class);
    }
}
