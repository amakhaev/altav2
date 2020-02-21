package com.alta_v2.physicsModule;

import com.alta_v2.physicsModule.executionContext.TiledMapEngineContext;
import com.alta_v2.renderingModule.tiledMapScreen.TiledMapState;
import com.alta_v2.renderingModule.tiledMapScreen.state.NpcState;
import com.alta_v2.renderingModule.tiledMapScreen.state.PlayerState;
import lombok.experimental.UtilityClass;

/**
 * Updates given state for new values from context.
 */
@UtilityClass
public class StateUpdater {

    public void updateAll(TiledMapState state, TiledMapEngineContext context) {
        updateTiledMapState(state, context);
        updatePlayerState(state.getPlayer(), context);
        state.getNpcStateList().forEach(npcState -> updateNpcState(npcState, context));
    }

    private void updateTiledMapState(TiledMapState state, TiledMapEngineContext context) {
        state.updateMapCoordinates(context.getFocusPointGlobal().getX(), context.getFocusPointGlobal().getY());
    }

    private void updatePlayerState(PlayerState playerState, TiledMapEngineContext context) {
        playerState.updateCoordinates(
                context.getPlayer().getGlobalPoint().getX(), context.getPlayer().getGlobalPoint().getY()
        );
        playerState.setView(context.getPlayer().getView().getValue());
        playerState.setAnimationEnabled(context.getPlayer().getIsMoving().getValue());
        playerState.setAnimationChangeTime(context.getPlayer().getIsMoving().getChangeTime());
    }

    private void updateNpcState(NpcState npcState, TiledMapEngineContext context) {
        if (!context.getNpcMap().containsKey(npcState.getId())) {
            return;
        }

        npcState.getCoordinates().x = context.getNpcMap().get(npcState.getId()).getGlobalPoint().getX();
        npcState.getCoordinates().y = context.getNpcMap().get(npcState.getId()).getGlobalPoint().getY();
    }

}
