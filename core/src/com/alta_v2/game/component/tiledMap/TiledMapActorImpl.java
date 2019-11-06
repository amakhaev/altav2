package com.alta_v2.game.component.tiledMap;

import com.alta_v2.game.utils.Resources;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Provides the actor that describes the tiled map.
 */
public class TiledMapActorImpl implements TiledMapActor {

    private final AssetManager assetManager;

    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;
    private OrthographicCamera camera;

    /**
     * Initialize new instance of {@link TiledMapActorImpl}.
     *
     * @param assetManager - the {@link AssetManager} instance.
     */
    @AssistedInject
    public TiledMapActorImpl(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create() {
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
    public void render() {
        this.camera.update();
        this.tiledMapRenderer.setView(this.camera);
        this.tiledMapRenderer.render();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        this.tiledMap.dispose();
    }
}
