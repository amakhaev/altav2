package com.alta_v2.physics.task.moveNpc;

import com.alta_v2.physics.executionContext.AltitudeMap;
import com.alta_v2.physics.executionContext.Tenant;
import com.alta_v2.physics.executionContext.reserveData.ReservableActor;
import com.alta_v2.physics.executionContext.reserveData.ReservablePoint;
import com.alta_v2.physics.task.MovementDirection;
import com.alta_v2.physics.task.ResultTiledMapTask;
import com.alta_v2.physics.task.resultObserver.BaseTaskResultObserver;
import com.alta_v2.physics.utils.NpcMovementCalculator;
import com.alta_v2.physics.utils.TiledMapPhysicCalculator;
import com.alta_v2.rendering.actors.PersonView;
import com.badlogic.gdx.math.Vector2;
import lombok.Builder;
import lombok.Getter;

public class MoveNpcTask implements ResultTiledMapTask {

    private static final float EXECUTION_TIME_SECONDS = 0.2f;

    @Builder
    private static MoveNpcTask create(MovementDirection direction,
                                      ReservableActor npc,
                                      Vector2 targetPointLocal,
                                      AltitudeMap altitudeMap,
                                      ReservablePoint focusPointGlobal) {
        return new MoveNpcTask(direction, npc, targetPointLocal, altitudeMap, focusPointGlobal);
    }

    private final Vector2 targetPointGlobal;
    private final Vector2 targetPointLocal;
    private final float distanceLengthX;
    private final float distanceLengthY;
    private final ReservableActor npc;
    private final AltitudeMap altitudeMap;
    private final ReservablePoint focusPointGlobal;
    private final Tenant tenant = new Tenant("move-npc-task");

    private float currentExecutionTime = 0;

    @Getter
    private boolean isCompleted = false;

    @Getter
    private final BaseTaskResultObserver result = new BaseTaskResultObserver();

    private MoveNpcTask(MovementDirection direction,
                        ReservableActor npc,
                        Vector2 targetPointLocal,
                        AltitudeMap altitudeMap,
                        ReservablePoint focusPointGlobal) {
        this.npc = npc;
        this.altitudeMap = altitudeMap;
        this.focusPointGlobal = focusPointGlobal;

        this.targetPointLocal = targetPointLocal;
        this.targetPointGlobal = new Vector2(
                TiledMapPhysicCalculator.localToGlobal(targetPointLocal.x, altitudeMap.getTileWidth()),
                TiledMapPhysicCalculator.localToGlobal(targetPointLocal.y, altitudeMap.getTileHeight())
        );
        this.distanceLengthX = (this.targetPointLocal.x - this.npc.getLocalPoint().getX()) * altitudeMap.getTileWidth();
        this.distanceLengthY = (this.targetPointLocal.y - this.npc.getLocalPoint().getY()) * altitudeMap.getTileHeight();

        this.npc.reserve(this.tenant);
        this.npc.getLocalPoint().reserve(this.tenant);
        this.npc.getGlobalPoint().reserve(this.tenant);
        this.npc.getView().reserve(this.tenant).setValue(MovementDirection.getPersonView(direction), this.tenant);
    }

    @Override
    public void act(float delta) {
        if (this.isCompleted) {
            return;
        }

        this.currentExecutionTime += delta;
        if (this.currentExecutionTime >= EXECUTION_TIME_SECONDS) {
            this.npc.getGlobalPoint().setValue(this.targetPointGlobal, this.tenant);
        } else {
            // calculates the percentage of current time
            float currentPercentage = this.currentExecutionTime / EXECUTION_TIME_SECONDS * 100;

            // value to be used for one act.
            float reduceValueX = TiledMapPhysicCalculator.percentByValue(this.distanceLengthX, currentPercentage);
            float reduceValueY = TiledMapPhysicCalculator.percentByValue(this.distanceLengthY, currentPercentage);

            float globalX = NpcMovementCalculator.getPositionX(
                    npc.getLocalPoint().getX(), this.focusPointGlobal.getX(), this.altitudeMap.getTileWidth()
            );

            float globalY = NpcMovementCalculator.getPositionY(
                    npc.getLocalPoint().getY(), this.focusPointGlobal.getY(), this.altitudeMap.getTileHeight()
            );

            float currentX = this.npc.getGlobalPoint().getX() == this.targetPointGlobal.x ?
                    this.npc.getGlobalPoint().getX() : globalX + reduceValueX;

            float currentY = this.npc.getGlobalPoint().getY() == this.targetPointGlobal.y ?
                    this.npc.getGlobalPoint().getY() : globalY + reduceValueY;

            this.npc.getGlobalPoint().setValue(currentX, currentY, this.tenant);
        }

        this.postAct();
    }

    private void postAct() {
        if (this.npc.getGlobalPoint().getX() != this.targetPointGlobal.x ||
                this.npc.getGlobalPoint().getY() != this.targetPointGlobal.y) {
            return;
        }

        this.altitudeMap.setPointStatus(
                (int) this.npc.getLocalPoint().getX(),
                (int) this.npc.getLocalPoint().getY(),
                AltitudeMap.PointAvailability.FREE
        );

        this.altitudeMap.setPointStatus(
                (int) this.targetPointLocal.x, (int) this.targetPointLocal.y, AltitudeMap.PointAvailability.NPC
        );

        this.npc.getLocalPoint().setValue(this.targetPointLocal, this.tenant).release(this.tenant);
        this.npc.getGlobalPoint().release(this.tenant);
        this.npc.getView().release(this.tenant);
        this.npc.release(this.tenant);
        this.isCompleted = true;
        this.result.submitComplete();
    }

    @Override
    public void destroy() {
        this.result.destroy();
    }
}
