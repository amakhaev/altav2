package com.alta_v2

import com.alta_v2.facade.coreApi.ScreenCoreApi
import com.alta_v2.facade.coreApi.ScreenCoreApiImpl
import com.alta_v2.facade.dialogApi.DialogCoreApi
import com.alta_v2.facade.dialogApi.DialogCoreApiImpl
import com.alta_v2.facade.tiledMapApi.TiledMapCoreApi
import com.alta_v2.facade.tiledMapApi.TiledMapCoreApiImpl
import com.alta_v2.game.ScreenManager
import com.alta_v2.game.overlay.OverlayFactory
import com.alta_v2.game.inputProcessor.InputProcessorFactory
import com.alta_v2.game.screen.GameScreenFactory
import com.alta_v2.mediator.ProcessMediator
import com.alta_v2.mediator.ProcessMediatorImpl
import com.alta_v2.mediator.screen.context.ContextFactory
import com.alta_v2.mediator.screen.context.ContextFactoryImpl
import com.alta_v2.mediator.serde.UpdaterFactory
import com.alta_v2.provider.AppConfigProvider
import com.alta_v2.provider.ComponentStyleProvider
import com.alta_v2.provider.ThemeProvider
import com.alta_v2.rendering.ScreenFactory
import com.alta_v2.rendering.ScreenStateFactory
import com.alta_v2.rendering.common.component.ComponentStyle
import com.alta_v2.rendering.common.component.animation.AnimationFactory
import com.alta_v2.rendering.common.component.dialog.TitleDialogStyleProvider
import com.alta_v2.rendering.dialog.DialogImpl
import com.alta_v2.rendering.dialog.Dialog
import com.alta_v2.rendering.config.AppConfig
import com.alta_v2.rendering.config.Theme
import com.alta_v2.rendering.tiledMapScreen.layout.LayoutFactory
import com.google.inject.AbstractModule
import com.google.inject.Singleton
import com.google.inject.assistedinject.FactoryModuleBuilder

class CoreInjector : AbstractModule() {
    override fun configure() {
        bind(Theme::class.java).toProvider(ThemeProvider::class.java).`in`(Singleton::class.java)
        bind(AppConfig::class.java).toProvider(AppConfigProvider::class.java).`in`(Singleton::class.java)
        bind(ComponentStyle::class.java).toProvider(ComponentStyleProvider::class.java).`in`(Singleton::class.java)

        initGame()
        initMediator()
        initRendering()
        initFacade()
    }

    private fun initGame() {
        install(FactoryModuleBuilder().build(InputProcessorFactory::class.java))
        install(FactoryModuleBuilder().build(OverlayFactory::class.java))
        install(FactoryModuleBuilder().build(ScreenFactory::class.java))
        install(FactoryModuleBuilder().build(GameScreenFactory::class.java))
        bind(ScreenManager::class.java).`in`(Singleton::class.java)
    }

    private fun initMediator() {
        install(FactoryModuleBuilder().build(UpdaterFactory::class.java))
        bind(ContextFactory::class.java).to(ContextFactoryImpl::class.java).`in`(Singleton::class.java)
        bind(ProcessMediator::class.java).to(ProcessMediatorImpl::class.java).`in`(Singleton::class.java)
    }

    private fun initRendering() {
        install(FactoryModuleBuilder().build(ScreenStateFactory::class.java))
        install(FactoryModuleBuilder().build(com.alta_v2.rendering.common.component.ComponentFactory::class.java))
        install(FactoryModuleBuilder().build(LayoutFactory::class.java))
        install(FactoryModuleBuilder().build(AnimationFactory::class.java))
        bind(TitleDialogStyleProvider::class.java).`in`(Singleton::class.java)
        bind(DialogImpl::class.java).`in`(Singleton::class.java)
        bind(Dialog::class.java).to(DialogImpl::class.java).`in`(Singleton::class.java)
    }

    private fun initFacade() {
        bind(ScreenCoreApi::class.java).to(ScreenCoreApiImpl::class.java).`in`(Singleton::class.java)
        bind(TiledMapCoreApi::class.java).to(TiledMapCoreApiImpl::class.java).`in`(Singleton::class.java)
        bind(DialogCoreApi::class.java).to(DialogCoreApiImpl::class.java).`in`(Singleton::class.java)
    }
}