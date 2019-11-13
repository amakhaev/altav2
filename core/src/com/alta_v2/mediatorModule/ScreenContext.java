package com.alta_v2.mediatorModule;

import com.alta_v2.mediatorModule.actionController.ActionController;
import com.alta_v2.mediatorModule.updater.Updater;
import com.alta_v2.renderingModule.Renderer;
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
    private final ActionController actionController;

    /**
     * Destroys the context.
     */
    public void destroy() {
        this.screenUpdater.destroy();
        this.screenRender.dispose();
    }
}
