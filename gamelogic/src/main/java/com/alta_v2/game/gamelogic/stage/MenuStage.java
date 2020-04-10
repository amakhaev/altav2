package com.alta_v2.game.gamelogic.stage;

import com.alta_v2.game.dao.facade.definition.DefinitionDaoApi;
import com.alta_v2.game.gamelogic.stage.event.ChangeMenuStageEvent;
import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class MenuStage extends AbstractStage {

    private final DefinitionDaoApi definitionDaoApi;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onActionBegin(ActionType action) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onActionFinish(ActionType action) {
        if (action != ActionType.BACK) {
            return;
        }

        ChangeMenuStageEvent event = new ChangeMenuStageEvent(definitionDaoApi.getMapDefinition(1001));
        this.changeStage(event);
    }
}
