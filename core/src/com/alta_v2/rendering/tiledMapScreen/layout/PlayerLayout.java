package com.alta_v2.rendering.tiledMapScreen.layout;

import com.alta_v2.rendering.ScreenState;
import com.alta_v2.rendering.actors.player.PlayerActor;
import com.alta_v2.rendering.tiledMapScreen.TiledMapState;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Provides the layout that responsible to render player layer.
 */
@Log4j2
@RequiredArgsConstructor
public class PlayerLayout implements Layout {

    private final AssetManager assetManager;
    private final String texturePath;

    private SpriteBatch batch;
    private PlayerActor playerActor;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        this.batch.dispose();
    }
}
