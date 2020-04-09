package com.alta_v2.physics.task.rotatePlayer;

import com.alta_v2.physics.executionContext.TiledMapEngineContext;
import com.alta_v2.physics.task.ExecutionChecker;
import com.alta_v2.rendering.tiledMapScreen.actors.PersonView;
import lombok.RequiredArgsConstructor;

/**
 * Provides the checker that indicates when player can be rotate.
 */
@RequiredArgsConstructor
public class RotatePlayerChecker implements ExecutionChecker {

    private final PersonView currentView;

    @Override
    public boolean canTaskBeExecuted(TiledMapEngineContext context) {
        return !context.getPlayer().getView().isReserved() && context.getPlayer().getView().getValue() != this.currentView;
    }
}
