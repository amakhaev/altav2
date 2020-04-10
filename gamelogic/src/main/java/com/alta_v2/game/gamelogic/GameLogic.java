package com.alta_v2.game.gamelogic;

import com.alta_v2.game.AltaV2;
import com.alta_v2.mediator.serde.ActionListener;
import lombok.Getter;

import javax.inject.Inject;

public class GameLogic {

    @Getter
    private final AltaV2 altaV2;

    @Inject
    public GameLogic(ActionListener actionListener, AltaV2 altaV2) {
        this.altaV2 = altaV2;
        this.altaV2.setInputListener(actionListener);
    }

}
