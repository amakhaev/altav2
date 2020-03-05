package com.alta_v2.mediatorModule.screen.context;

import com.alta_v2.mediatorModule.serde.Updater;
import com.alta_v2.physicsModule.PhysicEngine;
import com.alta_v2.renderingModule.Renderer;
import com.alta_v2.renderingModule.ScreenState;
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
