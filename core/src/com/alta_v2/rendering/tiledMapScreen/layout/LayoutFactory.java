package com.alta_v2.rendering.tiledMapScreen.layout;

import com.alta_v2.rendering.tiledMapScreen.layout.map.MapLayoutImpl;
import com.alta_v2.rendering.tiledMapScreen.layout.person.NpcLayout;
import com.alta_v2.rendering.tiledMapScreen.layout.person.PlayerLayout;
import com.badlogic.gdx.assets.AssetManager;

import java.util.Map;

public interface LayoutFactory {

    PlayerLayout createPlayerLayout(AssetManager assetManager, String texturePath);

    NpcLayout createNpcLayout(AssetManager assetManager, Map<Integer, String> npcTextures);

    MapLayoutImpl createMapLayout(AssetManager assetManager, String mapPath);
}
