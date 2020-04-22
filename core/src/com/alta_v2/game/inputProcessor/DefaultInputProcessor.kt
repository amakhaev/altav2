package com.alta_v2.game.inputProcessor

import com.alta_v2.mediator.serde.ActionListener
import com.alta_v2.mediator.serde.ActionType
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputAdapter
import com.google.inject.assistedinject.Assisted
import com.google.inject.assistedinject.AssistedInject
import mu.KotlinLogging

/**
 * Provides the default input processor.
 */
class DefaultInputProcessor @AssistedInject constructor(@param:Assisted private val actionListener: ActionListener) : InputAdapter() {

    private val log = KotlinLogging.logger {  }

    /**
     * {@inheritDoc}
     */
    override fun keyDown(keycode: Int): Boolean {
        val actionType = ActionListener.resolveAction(keycode)
        if (actionType == ActionType.UNKNOWN) {
            log.debug("Unknown type of action by key code {}", keycode)
            return false
        }
        actionListener.onActionBegin(actionType)
        return true
    }

    /**
     * {@inheritDoc}
     */
    override fun keyUp(keycode: Int): Boolean {
        if (keycode == 41) { // M
            log.info("Java heap size: " + Gdx.app.javaHeap / 1024 / 1024 + " MB")
            log.info("Native heap size: " + Gdx.app.nativeHeap / 1024 / 1024 + " MB")
        }
        val actionType = ActionListener.resolveAction(keycode)
        if (actionType == ActionType.UNKNOWN) {
            log.debug("Unknown type of action by key code {}", keycode)
            return false
        }
        actionListener.onActionFinish(actionType)
        return true
    }

}