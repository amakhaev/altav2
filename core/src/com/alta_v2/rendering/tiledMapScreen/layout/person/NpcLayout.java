package com.alta_v2.rendering.tiledMapScreen.layout.person;

import com.alta_v2.rendering.ScreenState;
import com.alta_v2.rendering.tiledMapScreen.actors.npc.NpcActor;
import com.alta_v2.rendering.tiledMapScreen.TiledMapState;
import com.alta_v2.rendering.tiledMapScreen.layout.Layout;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.common.collect.Maps;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import lombok.extern.log4j.Log4j2;

import java.util.Map;

/**
 * Provides the layout that responsible to render NPC layer.
 */
@Log4j2
public class NpcLayout implements Layout {

    private final AssetManager assetManager;
    private final Map<Integer, String> npcTextures;

    private Map<Integer, NpcActor> npcActorMap;
    private SpriteBatch batch;

    @AssistedInject
    public NpcLayout(@Assisted AssetManager assetManager, @Assisted Map<Integer, String> npcTextures) {
        this.assetManager = assetManager;
        this.npcTextures = npcTextures;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(ScreenState state) {
        if (this.npcTextures == null || this.npcTextures.isEmpty()) {
            return;
        }

        this.batch = new SpriteBatch();
        this.npcActorMap = Maps.newHashMapWithExpectedSize(npcTextures.size());
        this.npcTextures.forEach((key, value) -> this.npcActorMap.put(
                key,
                new NpcActor(
                        this.assetManager.get(value, Texture.class),
                        this.defaultTileWidth(),
                        this.defaultTileHeight()
                )
        ));
        this.npcTextures.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(float delta, ScreenState state) {
        if (!this.isNpcMapAvailable()) {
            return;
        }

        TiledMapState s = this.resolveClass(state);
        if (s == null) {
            return;
        }

        s.getNpcStateList().forEach(npcState -> {
            if (this.npcActorMap.containsKey(npcState.getId())) {
                this.batch.begin();
                this.npcActorMap.get(npcState.getId()).draw(this.batch, delta, npcState);
                this.batch.end();
            } else {
                log.debug("Npc with given Id {} not found", npcState.getId());
            }
        });
    }

    @Override
    public void destroy() {
        if (this.isNpcMapAvailable()) {
            this.npcActorMap.clear();
        }
    }

    private boolean isNpcMapAvailable() {
        return this.npcActorMap != null && !this.npcActorMap.isEmpty();
    }
}
