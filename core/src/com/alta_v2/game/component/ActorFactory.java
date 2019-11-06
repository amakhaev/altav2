package com.alta_v2.game.component;

import com.alta_v2.game.component.overlay.OverlayComponentImpl;
import com.alta_v2.game.component.player.PlayerActorImpl;
import com.alta_v2.game.component.tiledMap.TiledMapActorImpl;

/**
 * Provides the factory to create actors.
 */
public interface ActorFactory {

    /**
     * Creates the tiled map actor.
     *
     * @return created {@link TiledMapActorImpl} instance.
     */
    TiledMapActorImpl createTiledMapActor();

    /**
     * Creates the player actor.
     *
     * @return created {@link PlayerActorImpl} instance.
     */
    PlayerActorImpl createPlayerActor();

    /**
     * Creates the overlay actor.
     *
     * @return created {@link OverlayComponentImpl} instance.
     */
    OverlayComponentImpl createOverlayActor();

}
