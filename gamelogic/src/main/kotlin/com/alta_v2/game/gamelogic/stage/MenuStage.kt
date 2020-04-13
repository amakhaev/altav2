package com.alta_v2.game.gamelogic.stage

import com.alta_v2.game.dao.facade.definition.DefinitionDaoApi
import com.alta_v2.game.gamelogic.stage.event.ChangeMenuStageEvent
import com.alta_v2.mediator.serde.ActionListener.ActionType
import com.google.inject.Inject

class MenuStage @Inject constructor(private val definitionDaoApi: DefinitionDaoApi) : AbstractStage() {

    override fun onActionBegin(action: ActionType?) {
    }

    override fun onActionFinish(action: ActionType?) {
        if (action != ActionType.BACK) {
            return
        }

        val event = ChangeMenuStageEvent(definitionDaoApi.getMapDefinition(1001))
        changeStage(event)
    }
}