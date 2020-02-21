package com.alta_v2.mediatorModule.screen;

import com.alta_v2.model.NpcDefinitionModel;
import com.alta_v2.model.PlayerDefinitionModel;

import java.util.List;

public interface ContextFactory {

    /**
     * Creates the context for menu screen.
     */
    ScreenContext createMenuContext();

    /**
     * Creates the context for tiled map screen.
     */
    ScreenContext createTiledMapContext(PlayerDefinitionModel player, List<NpcDefinitionModel> npcList);

}
