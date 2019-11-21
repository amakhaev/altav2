package com.alta_v2.mediatorModule.menu;

import com.alta_v2.mediatorModule.serde.ActionController;
import lombok.extern.log4j.Log4j2;

/**
 * Provides the controller to handle actions in menu.
 */
@Log4j2
public class MenuActionController implements ActionController {

    /**
     * {@inheritDoc}
     */
    @Override
    public void onActionBegin(ActionType action) {
        log.info("MenuActionController: Begin action isn't implemented yet");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onActionFinish(ActionType action) {
        log.info("MenuActionController: Finish action isn't implemented yet");
    }
}
