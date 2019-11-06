package com.alta_v2.game.screen;

import com.alta_v2.game.component.ActorFactory;
import com.alta_v2.game.component.overlay.OverlayComponent;
import com.alta_v2.game.component.tiledMap.TiledMapActor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import lombok.extern.log4j.Log4j2;

/**
 * The screen that based on the tiled map.
 */
@Log4j2
public class TiledMapScreenImpl extends ScreenAdapter implements TiledMapScreen {

    private final static float FADE_DURATION = 0.25f;

    private final ActorFactory actorFactory;

    private InputListener inputListener;
    private Stage stage;

    private TiledMapActor tiledMapActor;
    private OverlayComponent overlayComponent;

    /**
     * Initialize new instance of {@link TiledMapScreenImpl}.
     * @param actorFactory  - the {@link ActorFactory} instance.
     * @param inputListener - the {@link InputListener} instance.
     */
    @AssistedInject
    public TiledMapScreenImpl(ActorFactory actorFactory, @Assisted InputListener inputListener) {
        this.inputListener = inputListener;
        this.actorFactory = actorFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show () {
        OrthographicCamera camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.x  = camera.viewportWidth / 2f;
        camera.position.y  = camera.viewportHeight / 2f;

        this.tiledMapActor = this.actorFactory.createTiledMapActor();
        this.tiledMapActor.create();

        this.overlayComponent = this.actorFactory.createOverlayActor();
        this.overlayComponent.show(FADE_DURATION);

        this.stage = new Stage(new ScreenViewport(camera));

        Group group = new Group();
        group.addActor(this.actorFactory.createPlayerActor());
        this.stage.addActor(group);

        this.stage.getRoot().getColor().a = 0f;
        this.stage.addAction(Actions.fadeIn(FADE_DURATION));
        this.setInputListener(this.inputListener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.tiledMapActor.render();

        this.stage.draw();
        this.stage.act(delta);

        this.overlayComponent.act(delta);
        this.overlayComponent.render(this.stage.getCamera().viewportWidth, this.stage.getCamera().viewportHeight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose () {
        this.stage.dispose();
        this.tiledMapActor.dispose();
        this.overlayComponent.dispose();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInputListener(InputListener inputListener) {
        this.stage.addListener(inputListener);
        Gdx.input.setInputProcessor(this.stage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fadeOutScreen(Runnable postAction) {
        this.overlayComponent.hide(FADE_DURATION, postAction);
        Gdx.input.setInputProcessor(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveAllBy(float x, float y) {
        this.stage.addAction(Actions.moveBy(x, y, 0.5f));
        // this.camera.translate(x, y);
    }
}
