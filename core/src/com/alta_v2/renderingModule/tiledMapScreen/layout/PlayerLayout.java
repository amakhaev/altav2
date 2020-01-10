package com.alta_v2.renderingModule.tiledMapScreen.layout;

import com.alta_v2.renderingModule.ScreenState;
import com.alta_v2.renderingModule.actors.PlayerActor;
import com.alta_v2.renderingModule.tiledMapScreen.TiledMapState;
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
        this.playerActor = new PlayerActor(this.assetManager.get(this.texturePath, Texture.class), 32, 32);
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
        this.playerActor.draw(
                this.batch,
                delta,
                s.getActorCoordinates().x,
                s.getActorCoordinates().y,
                s.getPersonView(),
                s.isPlayerAnimationEnabled(),
                s.getPlayerAnimationChangeTime()
        );
        this.batch.end();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        this.batch.dispose();
    }

    private TiledMapState resolveClass(ScreenState screenState) {
        try {
            return ScreenState.resolveState(screenState, TiledMapState.class);
        } catch (ClassCastException e) {
            log.error(e);
            return null;
        }
    }
}
