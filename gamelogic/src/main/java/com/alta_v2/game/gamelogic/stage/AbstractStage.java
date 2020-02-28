package com.alta_v2.game.gamelogic.stage;

import com.alta_v2.game.gamelogic.stage.event.ChangeMapEventHandler;
import com.alta_v2.game.gamelogic.stage.event.ChangeScreenEvent;
import com.alta_v2.game.gamelogic.stage.event.ChangeScreenEventHandler;
import lombok.extern.log4j.Log4j2;

@Log4j2
public abstract class AbstractStage implements Stage {

    private ChangeScreenEventHandler changeScreenHandler;
    private ChangeMapEventHandler changeMapHandler;

    /**
     * {@inheritDoc}
     */
    @Override
    public void subscribeToChangeScreen(ChangeScreenEventHandler handler) {
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

    protected void changeScreen(StageType targetStage) {
        if (this.changeScreenHandler == null) {
            log.info("Change screen handler is null.");
            return;
        }

        this.changeScreenHandler.handle(new ChangeScreenEvent(targetStage));
    }
}
