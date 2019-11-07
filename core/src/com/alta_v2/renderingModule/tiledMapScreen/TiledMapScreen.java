package com.alta_v2.renderingModule.tiledMapScreen;

import com.alta_v2.aop.dynamicAssetLoader.DynamicAssetLoader;
import com.alta_v2.game.utils.Resources;
import com.alta_v2.renderingModule.Renderable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Provides the screen that shows a tiled map.
 */
public class TiledMapScreen implements Renderable {

    private final AssetManager assetManager;

    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;
    private OrthographicCamera camera;

    /**
     * Initialize new instance of {@link TiledMapScreen}
     */
    @AssistedInject
    public TiledMapScreen() {
        this.assetManager = this.createAssets();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        this.tiledMap = this.assetManager.get(Resources.MAP_TEST, TiledMap.class);
        this.tiledMapRenderer = new OrthogonalTiledMapRenderer(this.tiledMap);

        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.camera.position.x  = this.camera.viewportWidth / 2f;
        this.camera.position.y  = this.camera.viewportHeight / 2f;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(float delta) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(float delta) {
        this.camera.update();
        this.tiledMapRenderer.setView(this.camera);
        this.tiledMapRenderer.render();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        this.assetManager.dispose();
        this.tiledMap.dispose();
    }

    @DynamicAssetLoader(tiledMap = Resources.MAP_TEST)
    protected AssetManager createAssets() {
        return new AssetManager();
    }
}
