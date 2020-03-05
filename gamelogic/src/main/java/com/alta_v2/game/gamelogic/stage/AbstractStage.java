package com.alta_v2.game.gamelogic.stage;

import com.alta_v2.game.gamelogic.stage.event.ChangeMapEventHandler;
import com.alta_v2.game.gamelogic.stage.event.ChangeStageEvent;
import com.alta_v2.game.gamelogic.stage.event.ChangeScreenHandler;
import lombok.extern.log4j.Log4j2;

@Log4j2
public abstract class AbstractStage implements Stage {

    private ChangeScreenHandler changeScreenHandler;
    private ChangeMapEventHandler changeMapHandler;

    /**
     * {@inheritDoc}
     */
    @Override
    public void subscribeToChangeScreen(ChangeScreenHandler handler) {
        this.changeScreenHandler = handler;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void subscribeToChangeMap(ChangeMapEventHandler handler) {
        this.changeMapHandler = handler;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
        this.changeMapHandler = null;
        this.changeScreenHandler = null;
    }

    protected void changeStage(ChangeStageEvent changeStageEvent) {
        if (this.changeScreenHandler == null) {
            log.info("Change screen handler is null.");
            return;
        }

        this.changeScreenHandler.handle(changeStageEvent);
    }
}
