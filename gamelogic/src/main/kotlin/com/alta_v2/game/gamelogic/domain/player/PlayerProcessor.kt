package com.alta_v2.game.gamelogic.domain.player

import com.alta_v2.common.Destroyable
import com.alta_v2.mediator.serde.ActionListener.ActionType

interface PlayerProcessor : Destroyable {

    fun actionBegin(action: ActionType)

    fun actionFinish(action: ActionType)

}