package com.alta_v2.game.actor;

import com.alta_v2.game.actor.overlay.OverlayActor;
import com.alta_v2.game.actor.overlay.OverlayActorImpl;
import com.alta_v2.game.actor.overlay.OverlayAnimationType;
import com.alta_v2.game.actor.player.PlayerActorImpl;
import com.alta_v2.game.actor.tiledMap.TiledMapActorImpl;

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
     * @return created {@link OverlayActorImpl} instance.
     */
    OverlayActorImpl createOverlayActor();

}
