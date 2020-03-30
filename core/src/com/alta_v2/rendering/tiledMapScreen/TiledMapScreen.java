package com.alta_v2.rendering.tiledMapScreen;

import com.alta_v2.rendering.Renderer;
import com.alta_v2.rendering.ScreenState;
import com.alta_v2.rendering.tiledMapScreen.layout.*;
import com.alta_v2.utils.AssetLoader;
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
        this.assetManager = AssetLoader.load(metadata.getMapPath(), metadata.getActorTextures());

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
}
