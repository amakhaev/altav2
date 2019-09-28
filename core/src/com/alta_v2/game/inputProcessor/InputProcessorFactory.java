package com.alta_v2.game.inputProcessor;

import com.alta_v2.game.ScreenSwitcher;

/**
 * Provides the factory for input processors.
 */
public interface InputProcessorFactory {

    /**
     * Creates the default input processor.
     *
     * @param screenSwitcher - the scenario switcher.
     * @return created {@link DefaultInputProcessor} instance.
     */
    DefaultInputProcessor createDefaultInputProcessor(ScreenSwitcher screenSwitcher);

}
