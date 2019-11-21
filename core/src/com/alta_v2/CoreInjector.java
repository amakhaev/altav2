package com.alta_v2;

import com.alta_v2.aop.dynamicAssetLoader.DynamicAssetHandler;
import com.alta_v2.aop.dynamicAssetLoader.DynamicAssetLoader;
import com.alta_v2.game.component.ComponentFactory;
import com.alta_v2.game.inputProcessor.InputProcessorFactory;
import com.alta_v2.game.screen.GameScreenFactory;
import com.alta_v2.mediatorModule.ProcessMediatorFactory;
import com.alta_v2.mediatorModule.serde.ActionControllerFactory;
import com.alta_v2.mediatorModule.serde.UpdaterFactory;
import com.alta_v2.renderingModule.ScreenFactory;
import com.alta_v2.renderingModule.ScreenStateFactory;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.matcher.Matchers;

public class CoreInjector extends AbstractModule {

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder().build(InputProcessorFactory.class));
        install(new FactoryModuleBuilder().build(ComponentFactory.class));
        install(new FactoryModuleBuilder().build(ScreenFactory.class));
        install(new FactoryModuleBuilder().build(ProcessMediatorFactory.class));
        install(new FactoryModuleBuilder().build(GameScreenFactory.class));
        install(new FactoryModuleBuilder().build(UpdaterFactory.class));
        install(new FactoryModuleBuilder().build(ActionControllerFactory.class));
        install(new FactoryModuleBuilder().build(ScreenStateFactory.class));

        // AOP
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(DynamicAssetLoader.class), new DynamicAssetHandler());
    }
}
