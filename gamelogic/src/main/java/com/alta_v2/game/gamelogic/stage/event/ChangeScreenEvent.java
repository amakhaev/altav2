package com.alta_v2.game.gamelogic.stage.event;

import com.alta_v2.game.gamelogic.stage.StageType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChangeScreenEvent {

    @Getter
    private final StageType targetStageType;

}
