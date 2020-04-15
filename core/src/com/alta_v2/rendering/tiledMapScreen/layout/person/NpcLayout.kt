package com.alta_v2.rendering.tiledMapScreen.layout.person

import com.alta_v2.rendering.ScreenState
import com.alta_v2.rendering.tiledMapScreen.TiledMapState
import com.alta_v2.rendering.tiledMapScreen.actors.npc.NpcActor
import com.alta_v2.rendering.tiledMapScreen.layout.ObjectLayout
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.google.common.collect.Maps
import com.google.inject.assistedinject.Assisted
import com.google.inject.assistedinject.AssistedInject
import lombok.extern.log4j.Log4j2
import mu.KotlinLogging

/**
 * Provides the layout that responsible to render NPC layer.
 */
@Log4j2
class NpcLayout @AssistedInject constructor(@param:Assisted private val assetManager: AssetManager,
                                            @param:Assisted private val npcTextures: MutableMap<Int, String>) : ObjectLayout {

    private val log = KotlinLogging.logger {  }

    private lateinit var npcActorMap: MutableMap<Int, NpcActor>
    private lateinit var batch: SpriteBatch

    override fun init(state: ScreenState, tileWidth: Int, tileHeight: Int) {
        if (npcTextures.isEmpty()) {
            return
        }

        batch = SpriteBatch()
        npcActorMap = Maps.newHashMapWithExpectedSize(npcTextures.size)

        npcTextures.forEach { (key: Int, value: String) ->
            npcActorMap[key] = NpcActor(assetManager.get(value, Texture::class.java), tileWidth, tileHeight)
        }
        npcTextures.clear()
    }

    /**
     * {@inheritDoc}
     */
    override fun init(state: ScreenState) {
        log.error("Not implemented yet")
    }

    /**
     * {@inheritDoc}
     */
    override fun render(delta: Float, state: ScreenState) {
        if (!isNpcMapAvailable()) {
            return
        }
        if (state is TiledMapState) {
            for (npcState in state.npcStateList) {
                if (npcActorMap.containsKey(npcState.id)) {
                    batch.begin()
                    npcActorMap[npcState.id]!!.draw(batch, delta, npcState)
                    batch.end()
                } else {
                    log.debug("Npc with given Id {} not found", npcState.id)
                }
            }
        } else {
            log.error("ScreeState has invalid type, expected: ${TiledMapState::class.java}, found ${state::class.java}")
        }
    }

    override fun destroy() {
        if (isNpcMapAvailable()) {
            npcActorMap.clear()
        }
    }

    private fun isNpcMapAvailable(): Boolean = npcActorMap.isNotEmpty()

}