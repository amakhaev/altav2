package com.alta_v2.game.inputProcessor;

import com.alta_v2.mediator.serde.ActionListener;

/**
 * Provides the factory for input processors.
 */
public interface InputProcessorFactory {

    /**
     * Creates the default input processor.
     *
     * @return created {@link DefaultInputProcessor} instance.
     */
    DefaultInputProcessor createDefaultInputProcessor(ActionListener actionListener);

}
