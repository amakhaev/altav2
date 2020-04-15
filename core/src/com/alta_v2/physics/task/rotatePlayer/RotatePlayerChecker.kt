package com.alta_v2.physics.task.rotatePlayer

import com.alta_v2.physics.executionContext.TiledMapEngineContext
import com.alta_v2.physics.task.ExecutionChecker
import com.alta_v2.rendering.tiledMapScreen.actors.PersonView
import lombok.RequiredArgsConstructor

/**
 * Provides the checker that indicates when player can be rotate.
 */
class RotatePlayerChecker(private val currentView: PersonView) : ExecutionChecker {

    override fun canTaskBeExecuted(context: TiledMapEngineContext): Boolean {
        return !context.player.view.isReserved() && context.player.view.getValue() != currentView
    }
}