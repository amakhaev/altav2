package com.alta_v2.game.gamelogic;

import com.alta_v2.CoreInjector;
import com.alta_v2.game.AltaV2;
import com.alta_v2.mediatorModule.serde.ActionListener;
import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.Getter;

import javax.inject.Inject;

public class GameLogic {

    @Getter
    private final AltaV2 altaV2;

    @Inject
    public GameLogic(ActionListener actionListener, AltaV2 altaV2) {
        // Injector coreInjector = Guice.createInjector(new CoreInjector());
        // this.altaV2 = coreInjector.getInstance(AltaV2.class);
        this.altaV2 = altaV2;
        this.altaV2.setInputListener(actionListener);
    }

}
