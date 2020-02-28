package com.alta_v2.game.gamelogic;

import com.alta_v2.game.gamelogic.stage.StageManager;
import com.alta_v2.mediatorModule.serde.ActionListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.inject.Inject;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class CoreActionListener implements ActionListener {

    private final StageManager stageManager;

    @Override
    public void onActionBegin(ActionType action) {
        if (this.stageManager.getCurrentListener() != null) {
            this.stageManager.getCurrentListener().onActionBegin(action);
        }
    }

    @Override
    public void onActionFinish(ActionType action) {
        if (this.stageManager.getCurrentListener() != null) {
            this.stageManager.getCurrentListener().onActionFinish(action);
        }
    }

    @Override
    public void destroy() {
        log.debug("Nothing to destroy");
    }
}
