package com.alta_v2.game.actor.tiledMap;

import com.badlogic.gdx.utils.Disposable;

/**
 * Provides the actor that describes the tiled map.
 */
public interface TiledMapActor extends Disposable {

    /**
     * Creates the tiled map actor.
     */
    void create();

    /**
     * Renders the actor.
     */
    void render();

}
