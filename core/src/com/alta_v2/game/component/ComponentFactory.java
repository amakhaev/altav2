package com.alta_v2.game.component;

import com.alta_v2.game.component.overlay.OverlayComponentImpl;

/**
 * Provides the factory to create actors.
 */
public interface ComponentFactory {

    /**
     * Creates the overlay actor.
     *
     * @return created {@link OverlayComponentImpl} instance.
     */
    OverlayComponentImpl createOverlayActor();

}
