package com.alta_v2.facade.tiledMapApi;

import com.alta_v2.mediator.ProcessMediator;
import com.alta_v2.physics.TiledMapPhysicEngine;
import com.alta_v2.physics.task.MovementDirection;
import com.alta_v2.physics.task.resultObserver.TaskResultObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.inject.Inject;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class TiledMapApiImpl implements TiledMapApi {

    private final ProcessMediator processMediator;

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskResultObserver performPlayerMovement(MovementDirection direction) {
        TiledMapPhysicEngine physicEngine = this.getEngineFromContext();
        if (physicEngine == null) {
            log.error("Current screen is null or has invalid type, required TiledMapScreen");
            return null;
        }

        return physicEngine.performPlayerMovement(direction);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskResultObserver performNpcMovement(int npcId, MovementDirection direction) {
        TiledMapPhysicEngine physicEngine = this.getEngineFromContext();
        if (physicEngine == null) {
            log.error("Current screen is null or has invalid type, required TiledMapScreen");
            return null;
        }

        return physicEngine.performNpcMovement(npcId, direction);
    }

    private TiledMapPhysicEngine getEngineFromContext() {
        return (this.processMediator.getCurrentContext().getPhysicEngine() != null &&
                this.processMediator.getCurrentContext().getPhysicEngine() instanceof TiledMapPhysicEngine) ?
                ((TiledMapPhysicEngine) this.processMediator.getCurrentContext().getPhysicEngine()) : null;
    }
}
