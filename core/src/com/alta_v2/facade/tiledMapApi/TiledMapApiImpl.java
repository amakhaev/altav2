package com.alta_v2.facade.tiledMapApi;

import com.alta_v2.mediatorModule.ProcessMediator;
import com.alta_v2.physicsModule.TiledMapPhysicEngine;
import com.alta_v2.physicsModule.task.MovementDirection;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.inject.Inject;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class TiledMapApiImpl implements TiledMapApi {

    private final ProcessMediator processMediator;

    @Override
    public void performPlayerMovement(MovementDirection direction) {
        TiledMapPhysicEngine physicEngine = this.getEngineFromContext();
        if (physicEngine == null) {
            log.error("Current screen is null or has invalid type, required TiledMapScreen");
            return;
        }

        physicEngine.performPlayerMovement(direction);
    }

    private TiledMapPhysicEngine getEngineFromContext() {
        return (this.processMediator.getCurrentContext().getPhysicEngine() != null &&
                this.processMediator.getCurrentContext().getPhysicEngine() instanceof TiledMapPhysicEngine) ?
                ((TiledMapPhysicEngine) this.processMediator.getCurrentContext().getPhysicEngine()) : null;
    }
}
