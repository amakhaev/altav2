package com.alta_v2.physics.task;

import com.alta_v2.physics.executionContext.TiledMapEngineContext;
import com.alta_v2.physics.executionContext.reserveData.ReservableActor;
import com.alta_v2.physics.task.moveNpc.MoveNpcChecker;
import com.alta_v2.physics.task.moveNpc.MoveNpcTask;
import com.alta_v2.physics.task.movePlayer.MovePlayerChecker;
import com.alta_v2.physics.task.movePlayer.MovePlayerTask;
import com.alta_v2.physics.task.rotatePlayer.RotateNpcChecker;
import com.alta_v2.physics.task.rotatePlayer.RotatePlayerChecker;
import com.alta_v2.physics.task.rotatePlayer.RotatePersonTask;
import com.alta_v2.physics.utils.MovementCalculator;
import com.alta_v2.rendering.tiledMapScreen.actors.PersonView;
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
     * @return crated {@link ResultTiledMapTask} instance or {@code}null{@code} if failed creation.
     */
    public ResultTiledMapTask createPlayerMoveTask(MovementDirection direction, TiledMapEngineContext context) {
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
     * @return crated {@link ResultTiledMapTask} instance or {@code}null{@code} if failed creation.
     */
    public ResultTiledMapTask createRotatePlayerTask(MovementDirection direction, TiledMapEngineContext context) {
        PersonView currentView = MovementDirection.getPersonView(direction);
        return new RotatePlayerChecker(currentView).canTaskBeExecuted(context)?
                new RotatePersonTask(direction, context.getPlayer().getView()) : null;
    }

    /**
     * Creates the task that calculates movement of NPC.
     *
     * @param direction - the direction of movement.
     * @param npcId     - the ID of NPC to be moved.
     * @param context   - the engine execution context.
     * @return created {@link ResultTiledMapTask} instance or {@code}null{@code} if creation failed.
     */
    public ResultTiledMapTask createNpcMoveTask(MovementDirection direction, int npcId, TiledMapEngineContext context) {
        MoveNpcChecker checker = new MoveNpcChecker(npcId);
        if (!checker.canTaskBeExecuted(context)) {
            log.trace("Failed to perform movement of Npc {} to the {} because process is blocked", npcId, direction);
            return null;
        }

        ReservableActor npc = context.getNpcMap().get(npcId);
        Vector2 targetPoint = MovementCalculator.getTargetPointLocal(
                direction, npc.getLocalPoint().getX(), npc.getLocalPoint().getY()
        );
        if (!MovementCalculator.canMoveTo(targetPoint.x, targetPoint.y, context.getAltitudeMap())) {
            log.debug("Failed to run npc moving since [{}: {}] is blocked", targetPoint.x, targetPoint.y);
            return null;
        }

        return MoveNpcTask.builder()
                .direction(direction)
                .npc(npc)
                .targetPointLocal(targetPoint)
                .altitudeMap(context.getAltitudeMap())
                .focusPointGlobal(context.getFocusPointGlobal())
                .build();
    }

    /**
     * Creates the task to rotate player.
     *
     * @param direction - the direction of movement.
     * @param npcId     - the ID of NPC to be moved.
     * @param context   - the engine execution context.
     * @return crated {@link ResultTiledMapTask} instance or {@code}null{@code} if failed creation.
     */
    public ResultTiledMapTask createRotateNpcTask(int npcId, MovementDirection direction, TiledMapEngineContext context) {
        PersonView currentView = MovementDirection.getPersonView(direction);
        return new RotateNpcChecker(npcId, currentView).canTaskBeExecuted(context) ?
                new RotatePersonTask(direction, context.getNpcMap().get(npcId).getView()) : null;
    }

}
