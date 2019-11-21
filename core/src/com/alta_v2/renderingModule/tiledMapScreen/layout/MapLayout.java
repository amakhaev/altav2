package com.alta_v2.renderingModule.tiledMapScreen.layout;

import com.alta_v2.renderingModule.ScreenState;
import com.alta_v2.renderingModule.tiledMapScreen.TiledMapState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Provides the implementation of map layout.
 */
@Log4j2
@RequiredArgsConstructor
public class MapLayout implements Layout {

    private final AssetManager assetManager;
    private final String mapPath;

    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;
    private OrthographicCamera camera;

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(ScreenState state) {
        this.tiledMap = this.assetManager.get(this.mapPath, TiledMap.class);
        this.tiledMapRenderer = new OrthogonalTiledMapRenderer(this.tiledMap);

        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        TiledMapState s = this.resolveClass(state);
        if (s != null) {
            this.camera.position.x = s.getMapCoordinates().x;
            this.camera.position.y = s.getMapCoordinates().y;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(float delta, ScreenState state) {
        TiledMapState s = this.resolveClass(state);
        if (s != null) {
            this.camera.position.x = s.getMapCoordinates().x;
            this.camera.position.y = s.getMapCoordinates().y;
        }

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

    private TiledMapState resolveClass(ScreenState screenState) {
        try {
            return ScreenState.resolveState(screenState, TiledMapState.class);
        } catch (ClassCastException e) {
            log.error(e);
            return null;
        }
    }
}
