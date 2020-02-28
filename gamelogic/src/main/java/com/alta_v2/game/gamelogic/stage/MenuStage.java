package com.alta_v2.game.gamelogic.stage;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MenuStage extends AbstractStage {

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
        if (action == ActionType.BACK) {
            this.changeScreen(StageType.MAP);
        }
    }


}
