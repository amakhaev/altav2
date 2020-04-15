package com.alta_v2.mediator.serde

import com.alta_v2.mediator.screen.menu.MenuScreenUpdater
import com.alta_v2.mediator.screen.tiledMap.TiledMapScreenUpdater
import com.alta_v2.physics.TiledMapPhysicEngine

/**
 * Provides the factory for creating of screen updaters.
 */
interface UpdaterFactory {
    /**
     * Creates the [MenuScreenUpdater] instance.
     */
    fun createMenuScreenUpdater(): MenuScreenUpdater

    /**
     * Creates the [TiledMapScreenUpdater] instance.
     */
    fun createTiledMapScreenUpdater(engine: TiledMapPhysicEngine): TiledMapScreenUpdater
}