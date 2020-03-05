package com.alta_v2.mediator.screen.context;

import com.alta_v2.model.MenuDefinitionModel;
import com.alta_v2.model.TiledMapDefinitionModel;

public interface ContextFactory {

    /**
     * Creates the context for menu screen.
     */
    ScreenContext createMenuContext(MenuDefinitionModel definition);

    /**
     * Creates the context for tiled map screen.
     */
    ScreenContext createTiledMapContext(TiledMapDefinitionModel definition);

}
