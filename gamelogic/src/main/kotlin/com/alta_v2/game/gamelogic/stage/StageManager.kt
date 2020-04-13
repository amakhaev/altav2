package com.alta_v2.game.gamelogic.stage

import com.alta_v2.mediator.serde.ActionListener

interface StageManager {

    /**
     * Gets listener related to current selected screen.
     */
    fun getCurrentListener(): ActionListener

}