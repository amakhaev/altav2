package com.alta_v2.game.gamelogic

import com.alta_v2.game.gamelogic.stage.StageManager
import com.alta_v2.mediator.serde.ActionListener
import com.alta_v2.mediator.serde.ActionType
import com.google.inject.Inject
import mu.KotlinLogging

private val log = KotlinLogging.logger {  }

class CoreActionListener @Inject constructor(private var stageManager: StageManager) : ActionListener {

    override fun onActionBegin(action: ActionType) {
        stageManager.getCurrentListener().onActionBegin(action)
    }

    override fun onActionFinish(action: ActionType) {
        stageManager.getCurrentListener().onActionFinish(action)
    }

    override fun destroy() = log.debug("Nothing to destroy")
}