package com.alta_v2;

import com.alta_v2.facade.coreApi.CoreApi;
import com.alta_v2.facade.coreApi.CoreApiImpl;
import com.alta_v2.facade.tiledMapApi.TiledMapApi;
import com.alta_v2.facade.tiledMapApi.TiledMapApiImpl;
import com.alta_v2.game.ScreenManager;
import com.alta_v2.game.inputProcessor.InputProcessorFactory;
import com.alta_v2.game.screen.GameScreenFactory;
import com.alta_v2.mediator.ProcessMediator;
import com.alta_v2.mediator.ProcessMediatorImpl;
import com.alta_v2.mediator.screen.context.ContextFactory;
import com.alta_v2.mediator.screen.context.ContextFactoryImpl;
import com.alta_v2.mediator.serde.UpdaterFactory;
import com.alta_v2.provider.AppConfigProvider;
import com.alta_v2.provider.ComponentStyleProvider;
import com.alta_v2.provider.ThemeProvider;
import com.alta_v2.rendering.ScreenFactory;
import com.alta_v2.rendering.ScreenStateFactory;
import com.alta_v2.rendering.common.component.ComponentFactory;
import com.alta_v2.rendering.common.component.ComponentStyle;
import com.alta_v2.rendering.common.component.animation.AnimationFactory;
import com.alta_v2.rendering.common.component.dialog.TitleDialogStyleProvider;
import com.alta_v2.rendering.config.AppConfig;
import com.alta_v2.rendering.config.Theme;
import com.alta_v2.rendering.tiledMapScreen.layout.LayoutFactory;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class CoreInjector extends AbstractModule {

    @Override
    protected void configure() {
        initGame();
        initMediator();
        initRendering();
        initFacade();
    }

    private void initGame() {
        install(new FactoryModuleBuilder().build(InputProcessorFactory.class));
        install(new FactoryModuleBuilder().build(com.alta_v2.game.component.ComponentFactory.class));
        install(new FactoryModuleBuilder().build(ScreenFactory.class));
        install(new FactoryModuleBuilder().build(GameScreenFactory.class));

        bind(ScreenManager.class).in(Singleton.class);
    }

    private void initMediator() {
        install(new FactoryModuleBuilder().build(UpdaterFactory.class));

        bind(ContextFactory.class).to(ContextFactoryImpl.class).in(Singleton.class);
        bind(ProcessMediator.class).to(ProcessMediatorImpl.class).in(Singleton.class);
    }

    private void initRendering() {
        install(new FactoryModuleBuilder().build(ScreenStateFactory.class));
        install(new FactoryModuleBuilder().build(ComponentFactory.class));
        install(new FactoryModuleBuilder().build(LayoutFactory.class));
        install(new FactoryModuleBuilder().build(AnimationFactory.class));

        bind(TitleDialogStyleProvider.class).in(Singleton.class);

        bind(Theme.class).toProvider(ThemeProvider.class).in(Singleton.class);
        bind(AppConfig.class).toProvider(AppConfigProvider.class).in(Singleton.class);
        bind(ComponentStyle.class).toProvider(ComponentStyleProvider.class).in(Singleton.class);
    }

    private void initFacade() {
        bind(CoreApi.class).to(CoreApiImpl.class).in(Singleton.class);
        bind(TiledMapApi.class).to(TiledMapApiImpl.class).in(Singleton.class);
    }
}
