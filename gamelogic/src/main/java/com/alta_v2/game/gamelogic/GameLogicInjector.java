package com.alta_v2.game.gamelogic;

import com.alta_v2.game.gamelogic.domain.npc.RepeatableActionProcessor;
import com.alta_v2.game.gamelogic.domain.npc.RepeatableActionProcessorImpl;
import com.alta_v2.game.gamelogic.domain.player.PlayerProcessor;
import com.alta_v2.game.gamelogic.domain.player.PlayerProcessorImpl;
import com.alta_v2.game.gamelogic.stage.StageFactory;
import com.alta_v2.game.gamelogic.stage.StageManager;
import com.alta_v2.game.gamelogic.stage.StageManagerImpl;
import com.alta_v2.mediator.serde.ActionListener;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class GameLogicInjector extends AbstractModule {

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder().build(StageFactory.class));

        bind(RepeatableActionProcessor.class).to(RepeatableActionProcessorImpl.class);
        bind(PlayerProcessor.class).to(PlayerProcessorImpl.class);

        bind(ActionListener.class).to(CoreActionListener.class).in(Singleton.class);
        bind(StageManager.class).to(StageManagerImpl.class).in(Singleton.class);
    }

}
