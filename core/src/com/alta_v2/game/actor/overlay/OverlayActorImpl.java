package com.alta_v2.game.actor.overlay;

import com.alta_v2.game.actor.tiledMap.TiledMapActorImpl;
import com.alta_v2.game.utils.Resources;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Provides the actor that will draw black overlay on full screen.
 */
public class OverlayActorImpl implements OverlayActor {

    private Runnable postAction;

    private Texture blackSquareTexture;
    private Batch batch;
    private FadeCalculator fadeCalculator;

    /**
     * Initialize new instance of {@link TiledMapActorImpl}.
     *
     * @param assetManager - the {@link AssetManager} instance.
     */
    @AssistedInject
    public OverlayActorImpl(AssetManager assetManager) {
        this.blackSquareTexture = assetManager.get(Resources.TEXTURE_BLACK_SQUARE, Texture.class);
        this.batch = new SpriteBatch();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void act(float delta) {
        if (!this.fadeCalculator.isCompleted()) {
            this.fadeCalculator.act(delta);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(float width, float height) {
        this.batch.begin();
        this.batch.setColor(0, 0, 0, this.fadeCalculator.getAlpha());
        this.batch.draw(this.blackSquareTexture, 0, 0, width, height);
        this.batch.end();

        if (this.fadeCalculator.isCompleted() && this.postAction != null) {
            this.postAction.run();
            this.postAction = null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show(float duration) {
        this.fadeCalculator = new FadeCalculator(duration, OverlayAnimationType.FADE_IN);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hide(float duration, Runnable postAction) {
        this.fadeCalculator = new FadeCalculator(duration, OverlayAnimationType.FADE_OUT);
        this.postAction = postAction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        this.batch.dispose();
        this.blackSquareTexture.dispose();
    }
}
