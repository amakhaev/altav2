package com.alta_v2.facade.coreApi

import com.alta_v2.game.utils.ChangeScreenResult
import com.alta_v2.model.MenuDefinitionModel
import com.alta_v2.model.TiledMapDefinitionModel

/**
 * Provides the API for core project.
 */
interface ScreenCoreApi {
    /**
     * Loads the menu screen.
     */
    fun loadMenuScreen(definition: MenuDefinitionModel): ChangeScreenResult

    /**
     * Loads a tiled map screen.
     */
    fun loadTiledMapScreen(definition: TiledMapDefinitionModel): ChangeScreenResult
}