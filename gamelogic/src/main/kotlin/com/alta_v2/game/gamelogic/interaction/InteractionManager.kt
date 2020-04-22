package com.alta_v2.game.gamelogic.interaction

import com.alta_v2.common.Destroyable
import com.alta_v2.game.gamelogic.stage.event.ChangeMapStageEvent
import com.alta_v2.mediator.serde.ActionType
import java.util.concurrent.CompletableFuture

interface InteractionManager : Destroyable {

    fun getChangeMapFuture(): CompletableFuture<ChangeMapStageEvent>

    fun onInitialized()

    fun onRepeatableAction(action: ActionType)

    fun onPress(action: ActionType)

}