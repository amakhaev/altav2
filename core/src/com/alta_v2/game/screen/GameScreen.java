package com.alta_v2.game.screen;

import com.alta_v2.game.component.ComponentFactory;
import com.alta_v2.game.component.overlay.OverlayComponent;
import com.alta_v2.mediatorModule.updater.Updater;
import com.alta_v2.renderingModule.Renderer;
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

    private final Renderer rendererComponent;
    private final Updater updater;
    private final ComponentFactory componentFactory;

    private OverlayComponent overlayComponent;

    /**
     * Initialize new instance of {@link GameScreen}.
     * @param componentFactory      - the {@link ComponentFactory} instance.
     * @param rendererComponent     - the {@link Renderer} instance.
     * @param updater               - the {@link Updater} instance.
     */
    @AssistedInject
    public GameScreen(ComponentFactory componentFactory,
                      @Assisted Renderer rendererComponent,
                      @Assisted Updater updater) {
        this.rendererComponent = rendererComponent;
        this.componentFactory = componentFactory;
        this.updater = updater;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void show () {
        this.overlayComponent = this.componentFactory.createOverlayActor();
        this.overlayComponent.show(FADE_DURATION);
        this.rendererComponent.init();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.updater.update(delta);
        this.rendererComponent.render(delta);

        this.overlayComponent.act(delta);
        this.overlayComponent.render(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose () {
        this.rendererComponent.dispose();
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
