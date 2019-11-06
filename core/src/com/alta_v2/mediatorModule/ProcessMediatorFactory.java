package com.alta_v2.mediatorModule;

import com.alta_v2.game.ScreenManager;

/**
 * Provides the factory yo create mediator.
 */
public interface ProcessMediatorFactory {

    /**
     * Creates the process mediator.
     *
     * @param screenManager - the {@link ScreenManager} instance.
     * @return created {@link ProcessMediator} instance.
     */
    ProcessMediatorImpl createProcessMediator(ScreenManager screenManager);

}
