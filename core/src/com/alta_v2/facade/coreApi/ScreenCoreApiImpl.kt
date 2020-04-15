package com.alta_v2.facade.coreApi

import com.alta_v2.game.utils.ChangeScreenResult
import com.alta_v2.mediator.ProcessMediator
import com.alta_v2.model.MenuDefinitionModel
import com.alta_v2.model.TiledMapDefinitionModel
import com.google.inject.Inject

class ScreenCoreApiImpl @Inject constructor(private val processMediator: ProcessMediator) : ScreenCoreApi {

    /**
     * {@inheritDoc}
     */
    override fun loadMenuScreen(definition: MenuDefinitionModel): ChangeScreenResult {
        return processMediator.loadMenuScreen(definition)
    }

    /**
     * {@inheritDoc}
     */
    override fun loadTiledMapScreen(definition: TiledMapDefinitionModel): ChangeScreenResult {
        return processMediator.loadTiledMapScreen(definition)
    }
}