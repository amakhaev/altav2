package com.alta_v2.game.gamelogic.data.npc;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class NpcModel {

    private final String id;
    private final float localX;
    private final float localY;
    private final int repeatMovementInterval = 5000;

    @Setter
    private long lastMovementMills;

    @Setter
    private boolean isMovementRunning;

}
