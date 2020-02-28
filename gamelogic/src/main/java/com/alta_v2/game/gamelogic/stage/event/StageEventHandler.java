package com.alta_v2.game.gamelogic.stage.event;

/**
 * Describes an event handler of stage.
 */
@FunctionalInterface
public interface StageEventHandler<T> {

    /**
     * Handles the event.
     *
     * @param data - the data related to event.
     */
    void handle(T data);

}
