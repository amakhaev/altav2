package com.alta_v2.game.component;

import com.alta_v2.game.component.overlay.OverlayComponentImpl;
import com.alta_v2.game.component.tiledMap.TiledMapActorImpl;

/**
 * Provides the factory to create actors.
 */
public interface ComponentFactory {

    /**
     * Creates the tiled map actor.
     *
     * @return created {@link TiledMapActorImpl} instance.
     */
    TiledMapActorImpl createTiledMapActor();

    /**
     * Creates the overlay actor.
     *
     * @return created {@link OverlayComponentImpl} instance.
     */
    OverlayComponentImpl createOverlayActor();

}
