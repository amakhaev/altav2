package com.alta_v2.game.gamelogic.stage.event

import com.alta_v2.model.MenuDefinitionModel
import com.alta_v2.model.TiledMapDefinitionModel

interface ChangeStageEvent

data class ChangeMapStageEvent(val mapDefinition: MenuDefinitionModel = MenuDefinitionModel()) : ChangeStageEvent
data class ChangeMenuStageEvent(val mapDefinition: TiledMapDefinitionModel) : ChangeStageEvent