package com.alta_v2.mediatorModule.screen;

import com.alta_v2.game.utils.Resources;
import com.alta_v2.mediatorModule.serde.UpdaterFactory;
import com.alta_v2.model.NpcDefinitionModel;
import com.alta_v2.model.PlayerDefinitionModel;
import com.alta_v2.physicsModule.TiledMapPhysicEngine;
import com.alta_v2.renderingModule.ScreenFactory;
import com.alta_v2.renderingModule.ScreenStateFactory;
import com.alta_v2.renderingModule.tiledMapScreen.TiledMapMetadata;
import com.badlogic.gdx.math.Vector2;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ContextFactoryImpl implements ContextFactory {

    private final ScreenFactory screenFactory;
    private final UpdaterFactory updaterFactory;
    private final ScreenStateFactory screenStateFactory;

    @Override
    public ScreenContext createMenuContext() {
        return new ScreenContext(
                this.updaterFactory.createMenuScreenUpdater(),
                this.screenFactory.createMenuScreen(),
                this.screenStateFactory.createMenuState(),
                null
        );
    }

    @Override
    public ScreenContext createTiledMapContext(PlayerDefinitionModel player, List<NpcDefinitionModel> npcList) {
        TiledMapMetadata metadata = new TiledMapMetadata(
                Resources.MAP_TEST,
                player.texturePath,
                npcList.stream().collect(Collectors.toMap(n -> n.id, n -> n.texturePath))
        );
        TiledMapPhysicEngine physicEngine = TiledMapPhysicEngine.builder()
                .mapPath(Resources.MAP_TEST)
                .playerId(player.id)
                .focusPointCoordinates(new Vector2(player.x, player.y))
                .npcList(npcList.stream().collect(Collectors.toMap(n -> n.id, n -> new Vector2(n.x, n.y))))
                .build();

        return new ScreenContext(
                this.updaterFactory.createTiledMapScreenUpdater(physicEngine),
                this.screenFactory.createTiledMapScreen(metadata),
                this.screenStateFactory.createTiledMapState(npcList.stream().map(n -> n.id).collect(Collectors.toList())),
                physicEngine
        );
    }
}
