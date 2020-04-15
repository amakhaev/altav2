package com.alta_v2.game

import com.alta_v2.game.inputProcessor.InputProcessorFactory
import com.alta_v2.mediator.ProcessMediator
import com.alta_v2.mediator.serde.ActionListener
import com.alta_v2.model.MenuDefinitionModel
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import com.google.inject.Inject
import kotlin.system.exitProcess

class AltaV2 @Inject constructor(private val inputProcessorFactory: InputProcessorFactory,
                                 private val processMediator: ProcessMediator,
                                 screenManager: ScreenManager) : Game() {

    private var inputProcessor: InputProcessor? = null

    init {
        screenManager.setGame(this)
    }

    /**
     * {@inheritDoc}
     */
    override fun create() {
        Gdx.input.inputProcessor = inputProcessor
        processMediator.loadMenuScreen(MenuDefinitionModel())
    }

    /**
     * {@inheritDoc}
     */
    override fun dispose() {
        exitProcess(0)
    }

    fun setInputListener(actionListener: ActionListener) {
        inputProcessor = inputProcessorFactory.createDefaultInputProcessor(actionListener)
    }
}