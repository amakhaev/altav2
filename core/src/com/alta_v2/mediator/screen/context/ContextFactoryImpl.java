package com.alta_v2.mediator.screen.context;

import com.alta_v2.model.MenuDefinitionModel;
import com.alta_v2.model.TiledMapDefinitionModel;
import com.alta_v2.mediator.serde.UpdaterFactory;
import com.alta_v2.physics.TiledMapPhysicEngine;
import com.alta_v2.rendering.ScreenFactory;
import com.alta_v2.rendering.ScreenStateFactory;
import com.alta_v2.rendering.tiledMapScreen.TiledMapMetadata;
import com.badlogic.gdx.math.Vector2;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ContextFactoryImpl implements ContextFactory {

    private final ScreenFactory screenFactory;
    private final UpdaterFactory updaterFactory;
    private final ScreenStateFactory screenStateFactory;

    @Override
    public ScreenContext createMenuContext(MenuDefinitionModel definition) {
        return new ScreenContext(
                this.updaterFactory.createMenuScreenUpdater(),
                this.screenFactory.createMenuScreen(),
                this.screenStateFactory.createMenuState(),
                null
        );
    }

    @Override
    public ScreenContext createTiledMapContext(TiledMapDefinitionModel definition) {
        TiledMapMetadata metadata = new TiledMapMetadata(
                definition.getMapPath(),
                definition.getPlayer().texturePath,
                definition.getNpcList().stream().collect(Collectors.toMap(n -> n.id, n -> n.texturePath))
        );
        TiledMapPhysicEngine physicEngine = TiledMapPhysicEngine.builder()
                .mapPath(definition.getMapPath())
                .playerId(definition.getPlayer().id)
                .focusPointCoordinates(new Vector2(definition.getPlayer().x, definition.getPlayer().y))
                .npcList(definition.getNpcList().stream().collect(Collectors.toMap(n -> n.id, n -> new Vector2(n.x, n.y))))
                .build();

        return new ScreenContext(
                this.updaterFactory.createTiledMapScreenUpdater(physicEngine),
                this.screenFactory.createTiledMapScreen(metadata),
                this.screenStateFactory.createTiledMapState(definition.getNpcList().stream().map(n -> n.id).collect(Collectors.toList())),
                physicEngine
        );
    }
}
