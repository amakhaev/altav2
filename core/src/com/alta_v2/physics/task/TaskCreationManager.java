package com.alta_v2.physics.task;

import com.alta_v2.physics.executionContext.TiledMapEngineContext;
import com.alta_v2.physics.task.movePlayer.MovePlayerChecker;
import com.alta_v2.physics.task.movePlayer.MovePlayerTask;
import com.alta_v2.physics.task.rotatePlayer.RotatePlayerChecker;
import com.alta_v2.physics.task.rotatePlayer.RotatePlayerTask;
import com.alta_v2.physics.utils.MovementCalculator;
import com.alta_v2.rendering.actors.PersonView;
import com.badlogic.gdx.math.Vector2;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

/**
 * Provides mediator to tasks creation manage.
 */
@Log4j2
@UtilityClass
public final class TaskCreationManager {

    /**
     * Creates the task that calculates movement of player.
     *
     * @param direction - the direction of movement.
     * @param context   - the engine execution context.
     * @return crated {@link TiledMapTask} instance or {@code}null{@code} if failed creation.
     */
    public TiledMapTask createPlayerMoveTask(MovementDirection direction, TiledMapEngineContext context) {
        MovePlayerChecker checker = new MovePlayerChecker();

        if (!checker.canTaskBeExecuted(context)) {
            log.trace("Failed to perform movement of focus point on the {} because process is blocked", direction);
            return null;
        }

        Vector2 targetPoint = MovementCalculator.getTargetPointLocal(
                direction, context.getFocusPointLocal().getX(), context.getFocusPointLocal().getY()
        );
        if (!MovementCalculator.canMoveTo(targetPoint.x, targetPoint.y, context.getAltitudeMap())) {
            log.debug("Failed to run player moving since [{}: {}] is blocked", targetPoint.x, targetPoint.y);
            return null;
        }

        return MovePlayerTask.builder()
                .altitudeMap(context.getAltitudeMap())
                .direction(direction)
                .focusPointGlobal(context.getFocusPointGlobal())
                .focusPointLocal(context.getFocusPointLocal())
                .playerPointLocal(context.getPlayer().getLocalPoint())
                .playerView(context.getPlayer().getView())
                .isPlayerMoving(context.getPlayer().getIsMoving())
                .targetPointLocal(targetPoint)
                .build();
    }

    /**
     * Creates the task to rotate player.
     *
     * @param direction - the direction of movement.
     * @param context   - the engine execution context.
     * @return crated {@link TiledMapTask} instance or {@code}null{@code} if failed creation.
     */
    public TiledMapTask createRotatePlayerTask(MovementDirection direction, TiledMapEngineContext context) {
        PersonView currentView = MovementDirection.getPersonView(direction);
        return new RotatePlayerChecker().canTaskBeExecuted(context) &&
                context.getPlayer().getView().getValue() != currentView ?
                new RotatePlayerTask(direction, context.getPlayer().getView()) : null;
    }

}
