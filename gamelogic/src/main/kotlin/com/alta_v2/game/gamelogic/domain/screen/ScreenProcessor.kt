package com.alta_v2.game.gamelogic.domain.screen

import com.alta_v2.game.utils.ChangeScreenResult
import com.alta_v2.model.MenuDefinitionModel
import com.alta_v2.model.TiledMapDefinitionModel

/**
 * Describes processor that works with screen changes.
 */
interface ScreenProcessor {

    /**
     * Shows menu screen.
     */
    fun showMenuScreen(definition: MenuDefinitionModel): ChangeScreenResult

    /**
     * Shows tiled map screen.
     */
    fun showMapScreen(definition: TiledMapDefinitionModel): ChangeScreenResult

}