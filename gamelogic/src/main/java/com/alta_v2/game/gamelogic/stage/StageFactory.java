package com.alta_v2.game.gamelogic.stage;

import com.alta_v2.game.gamelogic.data.map.MapModel;
import com.alta_v2.game.gamelogic.data.npc.NpcModel;

import java.util.List;

public interface StageFactory {

    MenuStage createMenuStage();
    MapStage createMapStage(MapModel mapModel, List<NpcModel> npcList);

}
