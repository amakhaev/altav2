package com.alta_v2.game.screen;

import com.alta_v2.game.component.ActorFactory;
import com.alta_v2.game.component.overlay.OverlayComponent;
import com.alta_v2.renderingModule.Renderable;
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

    private final Renderable renderableComponent;
    private final ActorFactory actorFactory;

    private OverlayComponent overlayComponent;

    /**
     * Initialize new instance of {@link GameScreen}.
     *
     * @param actorFactory          - the {@link ActorFactory} instance.
     * @param renderableComponent   - the {@link Renderable} instance.
     */
    @AssistedInject
    public GameScreen(ActorFactory actorFactory, @Assisted Renderable renderableComponent) {
        this.renderableComponent = renderableComponent;
        this.actorFactory = actorFactory;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void show () {
        this.overlayComponent = this.actorFactory.createOverlayActor();
        this.overlayComponent.show(FADE_DURATION);
        this.renderableComponent.init();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.renderableComponent.update(delta);
        this.renderableComponent.render(delta);

        this.overlayComponent.act(delta);
        this.overlayComponent.render(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose () {
        this.renderableComponent.dispose();
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
