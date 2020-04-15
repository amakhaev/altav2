package com.alta_v2.mediator

import com.alta_v2.game.ScreenManager
import com.alta_v2.game.utils.ChangeScreenResult
import com.alta_v2.mediator.screen.context.ContextFactory
import com.alta_v2.mediator.screen.context.ScreenContext
import com.alta_v2.model.MenuDefinitionModel
import com.alta_v2.model.TiledMapDefinitionModel
import com.google.inject.Inject

/**
 * Provides the mediator that responsible for orchestration of game.
 */
class ProcessMediatorImpl @Inject constructor(private val contextFactory: ContextFactory,
                                              private val screenManager: ScreenManager) : ProcessMediator {

    private var currentContext: ScreenContext? = null

    override fun getCurrentContext(): ScreenContext? = currentContext

    /**
     * {@inheritDoc}
     */
    override fun loadMenuScreen(definition: MenuDefinitionModel?): ChangeScreenResult {
        currentContext = contextFactory.createMenuContext(definition!!)
        return screenManager.changeScreen(currentContext)
    }

    /**
     * {@inheritDoc}
     */
    override fun loadTiledMapScreen(definition: TiledMapDefinitionModel?): ChangeScreenResult {
        currentContext = contextFactory.createTiledMapContext(definition!!)
        return screenManager.changeScreen(currentContext)
    }
}