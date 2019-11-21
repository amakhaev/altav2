package com.alta_v2.renderingModule.menuScreen;

import com.alta_v2.aop.dynamicAssetLoader.DynamicAssetLoader;
import com.alta_v2.game.utils.Resources;
import com.alta_v2.renderingModule.Renderer;
import com.alta_v2.renderingModule.ScreenState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.inject.assistedinject.AssistedInject;
import lombok.extern.log4j.Log4j2;

/**
 * Provides the menu screen.
 */
@Log4j2
public class MenuScreen implements Renderer {

    private final AssetManager assetManager;

    private Batch batch;
    private Texture backgroundTexture;


    /**
     * Initialize new instance of {@link MenuScreen}.
     */
    @AssistedInject
    public MenuScreen() {
        this.assetManager = this.createAssets();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(ScreenState state) {
        this.backgroundTexture = this.assetManager.get(Resources.TEXTURE_MENU_BACKGROUND, Texture.class);
        this.batch = new SpriteBatch();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(float delta, ScreenState state) {
        this.batch.begin();
        this.batch.draw(this.backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.batch.end();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        this.batch.dispose();
        this.backgroundTexture.dispose();
        this.assetManager.dispose();
    }

    @DynamicAssetLoader(textures = Resources.TEXTURE_MENU_BACKGROUND)
    protected AssetManager createAssets() {
        return new AssetManager();
    }
}
