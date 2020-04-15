package com.alta_v2.mediator

import com.alta_v2.game.utils.ChangeScreenResult
import com.alta_v2.mediator.screen.context.ScreenContext
import com.alta_v2.model.MenuDefinitionModel
import com.alta_v2.model.TiledMapDefinitionModel

/**
 * Provides the mediator that responsible for orchestration of game.
 */
interface ProcessMediator {
    /**
     * Gets context of current screen.
     */
    fun getCurrentContext(): ScreenContext?

    /**
     * Loads the menu screen.
     */
    fun loadMenuScreen(definition: MenuDefinitionModel?): ChangeScreenResult

    /**
     * Loads a tiled map screen.
     */
    fun loadTiledMapScreen(definition: TiledMapDefinitionModel?): ChangeScreenResult
}