package com.alta_v2.rendering;

/**
 * Provides the state of specific screen.
 */
public interface ScreenState {

    /**
     * Gets the instance of specific screen.
     */
    static <T extends ScreenState> T resolveState(ScreenState instance, Class<T> type) {
        if (type.isInstance(instance)) {
            return type.cast(instance);
        }

        throw new ClassCastException("Failed to cast screen state. Expected: " + type + ", actual: " + instance.getClass());
    }

}
