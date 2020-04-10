package com.alta_v2.rendering.tiledMapScreen;

import com.alta_v2.rendering.Renderer;
import com.alta_v2.rendering.ScreenState;
import com.alta_v2.rendering.tiledMapScreen.layout.Layout;
import com.alta_v2.rendering.tiledMapScreen.layout.LayoutFactory;
import com.alta_v2.rendering.tiledMapScreen.layout.map.MapLayout;
import com.alta_v2.utils.AssetLoader;
import com.badlogic.gdx.assets.AssetManager;
import com.google.common.collect.Lists;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import lombok.extern.log4j.Log4j2;

import java.util.List;

/**
 * Provides the screen that shows a tiled map.
 */
@Log4j2
public class TiledMapScreen implements Renderer {

    private final AssetManager assetManager;
    private final MapLayout mapLayout;

    private List<Layout> contentLayouts;

    /**
     * Initialize new instance of {@link TiledMapScreen}
     */
    @AssistedInject
    public TiledMapScreen(@Assisted TiledMapMetadata metadata, LayoutFactory layoutFactory) {
        assetManager = AssetLoader.load(metadata.getMapPath(), metadata.getActorTextures());

        mapLayout = layoutFactory.createMapLayout(assetManager, metadata.getMapPath());
        contentLayouts = Lists.newArrayList(
                layoutFactory.createPlayerLayout(assetManager, metadata.getActorTexturePath()),
                layoutFactory.createNpcLayout(assetManager, metadata.getNpcTextures())
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(ScreenState state) {
        mapLayout.init(state);
        contentLayouts.forEach(layout -> layout.init(state));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(float delta, ScreenState state) {
        mapLayout.applyState(state);
        mapLayout.renderBottomPart();
        contentLayouts.forEach(layout -> layout.render(delta, state));
        mapLayout.renderTopPart();
    }

    @Override
    public void destroy() {
        contentLayouts.forEach(Renderer::destroy);
        mapLayout.destroy();
        assetManager.dispose();
    }
}
