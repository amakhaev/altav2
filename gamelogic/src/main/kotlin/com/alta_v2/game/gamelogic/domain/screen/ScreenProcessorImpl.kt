package com.alta_v2.game.gamelogic.domain.screen

import com.alta_v2.facade.coreApi.ScreenCoreApi
import com.alta_v2.model.MenuDefinitionModel
import com.alta_v2.model.TiledMapDefinitionModel
import com.google.inject.Inject

class ScreenProcessorImpl @Inject constructor(private val screenCoreApi: ScreenCoreApi) : ScreenProcessor {

    override fun showMenuScreen(definition: MenuDefinitionModel) = screenCoreApi.loadMenuScreen(definition)

    override fun showMapScreen(definition: TiledMapDefinitionModel) = screenCoreApi.loadTiledMapScreen(definition)


}