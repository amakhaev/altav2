package com.alta_v2.mediator.screen.tiledMap

import com.alta_v2.mediator.serde.Updater
import com.alta_v2.physics.TiledMapPhysicEngine
import com.alta_v2.rendering.ScreenState
import com.alta_v2.rendering.tiledMapScreen.TiledMapState
import com.google.inject.assistedinject.Assisted
import com.google.inject.assistedinject.AssistedInject
import lombok.extern.log4j.Log4j2
import mu.KotlinLogging

/**
 * {@inheritDoc}
 */
class TiledMapScreenUpdater @AssistedInject constructor(@param:Assisted private val engine: TiledMapPhysicEngine) : Updater {

    private val log = KotlinLogging.logger {  }

    /**
     * {@inheritDoc}
     */
    override fun init(screenState: ScreenState) {
        engine.processInit()
    }

    /**
     * {@inheritDoc}
     */
    override fun update(delta: Float, screenState: ScreenState) {
        if (screenState !is TiledMapState) {
            return
        }

        try {
            engine.processAct(delta)
            engine.updateState(screenState)
        } catch (e: Exception) {
            log.error("Failed to update TiledMapScreen", e)
        }
    }

    /**
     * {@inheritDoc}
     */
    override fun destroy() {}

}