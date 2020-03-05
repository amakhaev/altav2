package com.alta_v2.game.gamelogic.stage;

import com.alta_v2.game.gamelogic.stage.event.ChangeMapEventHandler;
import com.alta_v2.game.gamelogic.stage.event.ChangeScreenHandler;
import com.alta_v2.mediator.serde.ActionListener;

/**
 * Provides base entity for game logic.
 */
public interface Stage extends ActionListener {

    /**
     * Subscribes for changing of screen on stage.
     *
     * @param handler - the handler of screen changing.
     */
    void subscribeToChangeScreen(ChangeScreenHandler handler);

    /**
     * Subscribes for changing of map on stage.
     *
     * @param handler - the handler of map changing.
     */
    void subscribeToChangeMap(ChangeMapEventHandler handler);
}
