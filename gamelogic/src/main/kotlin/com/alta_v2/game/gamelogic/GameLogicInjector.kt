package com.alta_v2.game.gamelogic

import com.alta_v2.game.gamelogic.domain.interaction.InteractionDaoService
import com.alta_v2.game.gamelogic.domain.interaction.InteractionDaoServiceImpl
import com.alta_v2.game.gamelogic.domain.map.MapDaoService
import com.alta_v2.game.gamelogic.domain.map.MapDaoServiceImpl
import com.alta_v2.game.gamelogic.domain.map.MapProcessor
import com.alta_v2.game.gamelogic.domain.map.MapProcessorImpl
import com.alta_v2.game.gamelogic.domain.npc.NpcManager
import com.alta_v2.game.gamelogic.domain.npc.NpcManagerImpl
import com.alta_v2.game.gamelogic.domain.npc.NpcProcessor
import com.alta_v2.game.gamelogic.domain.npc.NpcProcessorImpl
import com.alta_v2.game.gamelogic.domain.player.PlayerProcessor
import com.alta_v2.game.gamelogic.domain.player.PlayerProcessorImpl
import com.alta_v2.game.gamelogic.domain.screen.ScreenProcessor
import com.alta_v2.game.gamelogic.domain.screen.ScreenProcessorImpl
import com.alta_v2.game.gamelogic.interaction.InteractionFactory
import com.alta_v2.game.gamelogic.stage.StageFactory
import com.alta_v2.game.gamelogic.stage.StageManager
import com.alta_v2.game.gamelogic.stage.StageManagerImpl
import com.alta_v2.mediator.serde.ActionListener
import com.google.inject.AbstractModule
import com.google.inject.Singleton
import com.google.inject.assistedinject.FactoryModuleBuilder

class GameLogicInjector : AbstractModule() {

    override fun configure() {
        initFactories()
        initDomainLayer()
        initStageLayer()
    }

    private fun initFactories() {
        install(FactoryModuleBuilder().build(StageFactory::class.java))
        install(FactoryModuleBuilder().build(InteractionFactory::class.java))
    }

    private fun initDomainLayer() {
        bind(NpcProcessor::class.java).to(NpcProcessorImpl::class.java).`in`(Singleton::class.java)
        bind(PlayerProcessor::class.java).to(PlayerProcessorImpl::class.java).`in`(Singleton::class.java)
        bind(MapProcessor::class.java).to(MapProcessorImpl::class.java).`in`(Singleton::class.java)
        bind(ScreenProcessor::class.java).to(ScreenProcessorImpl::class.java).`in`(Singleton::class.java)

        bind(MapDaoService::class.java).to(MapDaoServiceImpl::class.java).`in`(Singleton::class.java)
        bind(InteractionDaoService::class.java).to(InteractionDaoServiceImpl::class.java).`in`(Singleton::class.java)

        bind(NpcManager::class.java).to(NpcManagerImpl::class.java)
    }

    private fun initStageLayer() {
        bind(ActionListener::class.java).to(CoreActionListener::class.java).`in`(Singleton::class.java)
        bind(StageManager::class.java).to(StageManagerImpl::class.java).`in`(Singleton::class.java)
    }
}