package com.alta_v2.game.gamelogic.stage.event;

public interface ChangeStageEvent {

    static <T extends ChangeStageEvent> T resolve(ChangeStageEvent instance, Class<T> type) {
        if (type.isInstance(instance)) {
            return type.cast(instance);
        }

        throw new ClassCastException("Failed to cast event. Expected: " + type + ", actual: " + instance.getClass());
    }

}
