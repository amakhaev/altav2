package com.alta_v2.game.screen;

import com.alta_v2.game.component.ComponentFactory;
import com.alta_v2.game.component.overlay.OverlayComponent;
import com.alta_v2.mediatorModule.screen.context.ScreenContext;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Provides the base screen-container for the game.
 */
public class GameScreen extends ScreenAdapter {

    private final static float FADE_DURATION = 0.25f;

    private final ScreenContext context;
    private final ComponentFactory componentFactory;

    private OverlayComponent overlayComponent;

    /**
     * Initialize new instance of {@link GameScreen}.
     * @param componentFactory  - the {@link ComponentFactory} instance.
     * @param screenContext     - the {@link ScreenContext} instance.
     */
    @AssistedInject
    public GameScreen(ComponentFactory componentFactory,
                      @Assisted ScreenContext screenContext) {
        this.context = screenContext;
        this.componentFactory = componentFactory;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void show () {
        this.overlayComponent = this.componentFactory.createOverlayActor();
        this.overlayComponent.show(FADE_DURATION);
        this.context.getScreenUpdater().init(this.context.getScreenState());
        this.context.getScreenRender().init(this.context.getScreenState());
    }

    @Override
    public void hide() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.context.getScreenUpdater().update(delta, this.context.getScreenState());
        this.context.getScreenRender().render(delta, this.context.getScreenState());

        this.overlayComponent.act(delta);
        this.overlayComponent.render(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose () {
        this.context.destroy();
        this.overlayComponent.dispose();
    }

    /**
     * Fades out the screen.
     *
     * @param postAction - the action to be run after screen hides.
     */
    public void fadeOutScreen(Runnable postAction) {
        this.overlayComponent.hide(FADE_DURATION, postAction);
    }
}
