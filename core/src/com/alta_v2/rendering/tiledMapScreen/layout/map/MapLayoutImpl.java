package com.alta_v2.rendering.tiledMapScreen.layout.map;

import com.alta_v2.rendering.ScreenState;
import com.alta_v2.rendering.tiledMapScreen.TiledMapState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Provides the implementation of map layout.
 */
@Log4j2
public class MapLayoutImpl implements MapLayout {

    private final static int[] BOTTOM_LAYERS = {0, 1};
    private final static int[] TOP_LAYERS = {2, 3};

    private final AssetManager assetManager;
    private final String mapPath;

    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;
    private OrthographicCamera camera;

    @AssistedInject
    public MapLayoutImpl(@Assisted AssetManager assetManager, @Assisted String mapPath) {
        this.assetManager = assetManager;
        this.mapPath = mapPath;
    }

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
    public void applyState(ScreenState state) {
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
    public void renderBottomPart() {
        this.camera.update();
        this.tiledMapRenderer.setView(this.camera);
        this.tiledMapRenderer.render(BOTTOM_LAYERS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void renderTopPart() {
        this.camera.update();
        this.tiledMapRenderer.setView(this.camera);
        this.tiledMapRenderer.render(TOP_LAYERS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
        tiledMap.dispose();
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
