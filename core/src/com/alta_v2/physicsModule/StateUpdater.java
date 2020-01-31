package com.alta_v2.physicsModule;

import com.alta_v2.physicsModule.executionContext.TiledMapEngineContext;
import com.alta_v2.renderingModule.tiledMapScreen.TiledMapState;
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

}
