package com.alta_v2.game.gamelogic.stage

import com.alta_v2.game.dao.facade.definition.DefinitionDaoApi
import com.alta_v2.game.gamelogic.stage.event.ChangeMenuStageEvent
import com.alta_v2.mediator.serde.ActionListener.ActionType
import com.google.inject.Inject
import mu.KotlinLogging

class MenuStage @Inject constructor(private val definitionDaoApi: DefinitionDaoApi) : AbstractStage() {

    private val log = KotlinLogging.logger {  }

    override fun onActionBegin(action: ActionType) {
    }

    override fun onActionFinish(action: ActionType) {
        if (action != ActionType.BACK) {
            return
        }

        val mapDefinition = definitionDaoApi.getMapDefinition(1001);
        if (mapDefinition == null) {
            log.warn("Map definition for given mapId: 1001 is null")
            return
        }

        val event = ChangeMenuStageEvent(mapDefinition)
        changeStage(event)
    }
}