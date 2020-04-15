package com.alta_v2.game.gamelogic

import com.alta_v2.game.AltaV2
import com.alta_v2.mediator.serde.ActionListener
import com.google.inject.Inject

class GameLogic @Inject constructor(val altaV2: AltaV2, actionListener: ActionListener) {

    init {
        altaV2.setInputListener(actionListener)
    }

}