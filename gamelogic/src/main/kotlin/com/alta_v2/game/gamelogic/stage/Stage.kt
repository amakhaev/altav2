package com.alta_v2.game.gamelogic.stage

import com.alta_v2.game.gamelogic.stage.event.ChangeMapEventHandler
import com.alta_v2.game.gamelogic.stage.event.ChangeScreenHandler
import com.alta_v2.mediator.serde.ActionListener

/**
 * Provides base entity for game logic.
 */
interface Stage : ActionListener {

    /**
     * Subscribes for changing of screen on stage.
     *
     * @param handler - the handler of screen changing.
     */
    fun subscribeToChangeScreen(handler: ChangeScreenHandler)

    /**
     * Subscribes for changing of map on stage.
     *
     * @param handler - the handler of map changing.
     */
    fun subscribeToChangeMap(handler: ChangeMapEventHandler)

    /**
     * Handles complete event of stage loading.
     */
    fun onStageLoaded()

}