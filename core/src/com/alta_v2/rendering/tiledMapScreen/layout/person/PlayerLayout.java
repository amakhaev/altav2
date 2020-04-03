package com.alta_v2.rendering.tiledMapScreen.layout.person;

import com.alta_v2.rendering.ScreenState;
import com.alta_v2.rendering.actors.player.PlayerActor;
import com.alta_v2.rendering.tiledMapScreen.TiledMapState;
import com.alta_v2.rendering.tiledMapScreen.layout.Layout;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import lombok.extern.log4j.Log4j2;

/**
 * Provides the layout that responsible to render player layer.
 */
@Log4j2
public class PlayerLayout implements Layout {

    private final AssetManager assetManager;
    private final String texturePath;

    private SpriteBatch batch;
    private PlayerActor playerActor;

    @AssistedInject
    public PlayerLayout(@Assisted AssetManager assetManager, @Assisted String texturePath) {
        this.assetManager = assetManager;
        this.texturePath = texturePath;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(ScreenState state) {
        this.batch = new SpriteBatch();
        this.playerActor = new PlayerActor(
                this.assetManager.get(this.texturePath, Texture.class),
                this.defaultTileWidth(),
                this.defaultTileHeight()
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(float delta, ScreenState state) {
        TiledMapState s = this.resolveClass(state);
        if (s == null) {
            return;
        }

        this.batch.begin();
        this.playerActor.draw(this.batch, delta, s.getPlayer());
        this.batch.end();
    }

    @Override
    public void destroy() {
        batch.dispose();
    }
}
