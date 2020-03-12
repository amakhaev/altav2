package com.alta_v2.game.gamelogic.domain.player;

import com.alta_v2.common.Destroyable;
import com.alta_v2.mediator.serde.ActionListener;

public interface PlayerProcessor extends Destroyable {

    void actionBegin(ActionListener.ActionType action);

    void actionFinish(ActionListener.ActionType action);

}
