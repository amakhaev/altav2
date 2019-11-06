package com.alta_v2.game.inputProcessor;

import com.alta_v2.mediatorModule.ProcessMediator;

/**
 * Provides the factory for input processors.
 */
public interface InputProcessorFactory {

    /**
     * Creates the default input processor.
     *
     * @param processMediator - the {@link ProcessMediator} instance.
     * @return created {@link DefaultInputProcessor} instance.
     */
    DefaultInputProcessor createDefaultInputProcessor(ProcessMediator processMediator);

}
