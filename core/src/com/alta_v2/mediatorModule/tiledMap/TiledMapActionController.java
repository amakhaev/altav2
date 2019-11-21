package com.alta_v2.mediatorModule.tiledMap;

import com.alta_v2.mediatorModule.serde.ActionController;
import lombok.extern.log4j.Log4j2;

/**
 * Provides the controller of actions on tiled map.
 */
@Log4j2
public class TiledMapActionController implements ActionController {

    /**
     * {@inheritDoc}
     */
    @Override
    public void onActionBegin(ActionType action) {
        log.info("Action begin {}", action);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onActionFinish(ActionType action) {
        log.info("Action finish {}", action);
    }
}
