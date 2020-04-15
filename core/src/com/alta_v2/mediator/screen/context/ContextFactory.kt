package com.alta_v2.mediator.screen.context

import com.alta_v2.model.MenuDefinitionModel
import com.alta_v2.model.TiledMapDefinitionModel

interface ContextFactory {
    /**
     * Creates the context for menu screen.
     */
    fun createMenuContext(definition: MenuDefinitionModel): ScreenContext

    /**
     * Creates the context for tiled map screen.
     */
    fun createTiledMapContext(definition: TiledMapDefinitionModel): ScreenContext
}