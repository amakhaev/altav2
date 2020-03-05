package com.alta_v2.game.gamelogic.stage.event;

import com.alta_v2.model.TiledMapDefinitionModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ChangeMenuStageEvent implements ChangeStageEvent {

    private final TiledMapDefinitionModel mapDefinition;

}
