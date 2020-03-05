package com.alta_v2.game.gamelogic.stage.event;

import com.alta_v2.model.MenuDefinitionModel;
import lombok.Getter;

@Getter
public class ChangeMapStageEvent implements ChangeStageEvent {

    private final MenuDefinitionModel mapDefinition = new MenuDefinitionModel();

}
