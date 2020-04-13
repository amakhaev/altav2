package com.alta_v2.game.gamelogic.stage.event

/**
 * Describes an event handler of stage.
 */
interface StageEventHandler<T> {

    /**
     * Handles the event.
     *
     * @param data - the data related to event.
     */
    fun handle(data: T)

}

interface ChangeScreenHandler : StageEventHandler<ChangeStageEvent>
interface ChangeMapEventHandler : StageEventHandler<Void>