package com.alta_v2.game.controller;

/**
 * Provides the controller of tiled map screen.
 */
public interface TiledMapController {

    /**
     * Moves stage by given target point.
     *
     * @param x - the target X by be moved.
     * @param y - the target Y by be moved.
     */
    void moveBy(float x, float y);

}
