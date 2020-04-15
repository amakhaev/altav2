package com.alta_v2.mediator.screen.context

import com.alta_v2.mediator.serde.UpdaterFactory
import com.alta_v2.model.MenuDefinitionModel
import com.alta_v2.model.NpcDefinitionModel
import com.alta_v2.model.TiledMapDefinitionModel
import com.alta_v2.physics.MenuPhysicEngine
import com.alta_v2.physics.TiledMapPhysicEngine
import com.alta_v2.rendering.ScreenFactory
import com.alta_v2.rendering.ScreenStateFactory
import com.alta_v2.rendering.dialog.DialogImpl
import com.alta_v2.rendering.dialog.DialogState
import com.alta_v2.rendering.tiledMapScreen.TiledMapMetadata
import com.badlogic.gdx.math.Vector2
import com.google.inject.Inject
import java.util.stream.Collectors

class ContextFactoryImpl @Inject constructor(private val screenFactory: ScreenFactory,
                                             private val updaterFactory: UpdaterFactory,
                                             private val screenStateFactory: ScreenStateFactory,
                                             private val dialog: DialogImpl) : ContextFactory {

    override fun createMenuContext(definition: MenuDefinitionModel): ScreenContext {
        return ScreenContext(
                screenUpdater = updaterFactory.createMenuScreenUpdater(),
                screenRender = screenFactory.createMenuScreen(),
                screenState = screenStateFactory.createMenuState(),
                dialogRender = dialog,
                dialogState = DialogState(),
                physicEngine = MenuPhysicEngine()
        )
    }

    override fun createTiledMapContext(definition: TiledMapDefinitionModel): ScreenContext {
        val metadata = TiledMapMetadata(
                mapPath = definition.mapPath,
                actorTexturePath = definition.player.texturePath,
                npcTextures = definition.npcPaths
        )

        val physicEngine = TiledMapPhysicEngine(
                mapPath = definition.mapPath,
                playerId = definition.player.id,
                focusPointCoordinates = Vector2(definition.player.x, definition.player.y),
                npcList = definition.npcList.stream().collect(Collectors.toMap({ obj: NpcDefinitionModel -> obj.id }) { n: NpcDefinitionModel -> Vector2(n.x, n.y) })
        )

        return ScreenContext(
                screenUpdater = updaterFactory.createTiledMapScreenUpdater(physicEngine),
                screenRender = screenFactory.createTiledMapScreen(metadata),
                screenState = screenStateFactory.createTiledMapState(definition.npcIds),
                dialogRender = dialog,
                dialogState = DialogState(),
                physicEngine = physicEngine
        )
    }
}