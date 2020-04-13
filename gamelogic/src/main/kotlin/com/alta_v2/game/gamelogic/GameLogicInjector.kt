package com.alta_v2.game.gamelogic

import com.alta_v2.game.gamelogic.domain.npc.RepeatableActionProcessor
import com.alta_v2.game.gamelogic.domain.npc.RepeatableActionProcessorImpl
import com.alta_v2.game.gamelogic.domain.player.PlayerProcessor
import com.alta_v2.game.gamelogic.domain.player.PlayerProcessorImpl
import com.alta_v2.game.gamelogic.stage.StageFactory
import com.alta_v2.game.gamelogic.stage.StageManager
import com.alta_v2.game.gamelogic.stage.StageManagerImpl
import com.alta_v2.mediator.serde.ActionListener
import com.google.inject.AbstractModule
import com.google.inject.Singleton
import com.google.inject.assistedinject.FactoryModuleBuilder

class GameLogicInjector : AbstractModule() {

    override fun configure() {
        install(FactoryModuleBuilder().build(StageFactory::class.java))
        bind(RepeatableActionProcessor::class.java).to(RepeatableActionProcessorImpl::class.java)
        bind(PlayerProcessor::class.java).to(PlayerProcessorImpl::class.java)
        bind(ActionListener::class.java).to(CoreActionListener::class.java).`in`(Singleton::class.java)
        bind(StageManager::class.java).to(StageManagerImpl::class.java).`in`(Singleton::class.java)
    }

}