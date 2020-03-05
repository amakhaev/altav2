package com.alta_v2.mediator.screen.context;

import com.alta_v2.mediator.serde.Updater;
import com.alta_v2.physics.PhysicEngine;
import com.alta_v2.rendering.Renderer;
import com.alta_v2.rendering.ScreenState;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Provides the context for screen. It's required object for rendering of any screen.
 */
@RequiredArgsConstructor
public final class ScreenContext {

    @Getter
    private final Updater screenUpdater;

    @Getter
    private final Renderer screenRender;

    @Getter
    private final ScreenState screenState;

    @Getter
    private final PhysicEngine physicEngine;

    /**
     * Destroys the context.
     */
    public void destroy() {
        this.screenUpdater.destroy();
        this.screenRender.dispose();
    }
}
