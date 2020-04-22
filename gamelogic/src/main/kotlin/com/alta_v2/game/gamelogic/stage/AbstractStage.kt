package com.alta_v2.game.gamelogic.stage

import com.alta_v2.game.gamelogic.stage.event.ChangeMapEventHandler
import com.alta_v2.game.gamelogic.stage.event.ChangeScreenHandler
import com.alta_v2.game.gamelogic.stage.event.ChangeStageEvent
import mu.KotlinLogging

private val log = KotlinLogging.logger {  }

abstract class AbstractStage : Stage {

    private var changeScreenHandler: ChangeScreenHandler? = null
    private var changeMapHandler: ChangeMapEventHandler? = null

    /**
     * {@inheritDoc}
     */
    override fun subscribeToChangeScreen(handler: ChangeScreenHandler) {
        this.changeScreenHandler = handler
    }

    /**
     * {@inheritDoc}
     */
    override fun subscribeToChangeMap(handler: ChangeMapEventHandler) {
        changeMapHandler = handler
    }

    /**
     * {@inheritDoc}
     */
    override fun onStageLoaded() {}

    /**
     * {@inheritDoc}
     */
    override fun destroy() {
        changeMapHandler = null
        changeScreenHandler = null
    }

    protected open fun changeStage(changeStageEvent: ChangeStageEvent) {
        if (changeScreenHandler == null) {
            log.info("Change screen handler is null.")
            return
        }
        changeScreenHandler!!.handle(changeStageEvent)
    }

}