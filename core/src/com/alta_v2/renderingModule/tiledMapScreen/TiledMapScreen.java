package com.alta_v2.renderingModule.tiledMapScreen;

import com.alta_v2.aop.dynamicAssetLoader.DynamicAssetLoader;
import com.alta_v2.game.utils.Resources;
import com.alta_v2.renderingModule.Renderer;
import com.alta_v2.renderingModule.ScreenState;
import com.alta_v2.renderingModule.tiledMapScreen.layout.*;
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
    private final MapLayout mapLayout;

    private List<Layout> layouts;

    /**
     * Initialize new instance of {@link TiledMapScreen}
     */
    @AssistedInject
    public TiledMapScreen(@Assisted TiledMapMetadata metadata) {
        this.assetManager = this.createAssets();
        this.mapLayout = new MapLayoutImpl(this.assetManager, metadata.getMapPath());
        this.layouts = Arrays.asList(
                new PlayerLayout(this.assetManager, metadata.getActorTexturePath()),
                new NpcLayout(this.assetManager, metadata.getNpcTextures())
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(ScreenState state) {
        this.mapLayout.init(state);
        this.layouts.forEach(layout -> layout.init(state));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(float delta, ScreenState state) {
        this.mapLayout.applyState(state);
        this.mapLayout.renderBottomPart();

        this.layouts.forEach(layout -> layout.render(delta, state));

        this.mapLayout.renderTopPart();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        this.layouts.forEach(Renderer::dispose);
        this.mapLayout.dispose();
        this.assetManager.dispose();
    }

    @DynamicAssetLoader(tiledMap = Resources.MAP_TEST,
            textures = { Resources.ACTOR_PERSON_12, Resources.CHILD_1, Resources.CHILD_2 })
    protected AssetManager createAssets() {
        return new AssetManager();
    }
}
