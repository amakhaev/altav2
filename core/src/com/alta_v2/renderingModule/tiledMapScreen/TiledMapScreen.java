package com.alta_v2.renderingModule.tiledMapScreen;

import com.alta_v2.aop.dynamicAssetLoader.DynamicAssetLoader;
import com.alta_v2.game.utils.Resources;
import com.alta_v2.renderingModule.Renderer;
import com.alta_v2.renderingModule.ScreenState;
import com.alta_v2.renderingModule.tiledMapScreen.layout.Layout;
import com.alta_v2.renderingModule.tiledMapScreen.layout.MapLayout;
import com.alta_v2.renderingModule.tiledMapScreen.layout.PlayerLayout;
import com.badlogic.gdx.assets.AssetManager;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.List;

/**
 * Provides the screen that shows a tiled map.
 */
@Log4j2
public class TiledMapScreen implements Renderer {

    private final AssetManager assetManager;

    private List<Layout> layouts;

    /**
     * Initialize new instance of {@link TiledMapScreen}
     */
    @AssistedInject
    public TiledMapScreen(@Assisted TiledMapMetadata metadata) {
        this.assetManager = this.createAssets();
        this.layouts = Arrays.asList(
                new MapLayout(this.assetManager, metadata.getMapPath()),
                new PlayerLayout(this.assetManager, metadata.getActorTexturePath())
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(ScreenState state) {
        this.layouts.forEach(layout -> layout.init(state));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(float delta, ScreenState state) {
        this.layouts.forEach(layout -> layout.render(delta, state));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        this.layouts.forEach(Renderer::dispose);
        this.assetManager.dispose();
    }

    @DynamicAssetLoader(tiledMap = Resources.MAP_TEST, textures = Resources.ACTOR_PERSON_12)
    protected AssetManager createAssets() {
        return new AssetManager();
    }
}
