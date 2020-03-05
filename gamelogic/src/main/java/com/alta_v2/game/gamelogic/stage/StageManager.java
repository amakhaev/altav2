package com.alta_v2.game.gamelogic.stage;

import com.alta_v2.mediator.serde.ActionListener;

public interface StageManager {

    /**
     * Gets listener related to current selected screen.
     */
    ActionListener getCurrentListener();

}
