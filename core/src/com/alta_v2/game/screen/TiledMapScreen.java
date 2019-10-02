package com.alta_v2.game.screen;

import com.alta_v2.game.actor.MyTestActor;
import com.alta_v2.game.actor.SecondTestActor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import lombok.extern.log4j.Log4j2;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;

/**
 * The screen that based on the tiled map.
 */
@Log4j2
public class TiledMapScreen extends ScreenAdapter {

    private final static float FADE_DURATION = 0.25f;

    private InputListener inputListener;
    private Stage stage;

    /**
     * Initialize new instance of {@link TiledMapScreen}.
     *
     * @param inputListener - the {@link InputListener} instance.
     */
    @AssistedInject
    public TiledMapScreen(@Assisted InputListener inputListener) {
        this.inputListener = inputListener;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show () {
        OrthographicCamera camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.x  = camera.viewportWidth / 2f;
        camera.position.y  = camera.viewportHeight / 2f;

        this.stage = new Stage(new ScreenViewport(camera));

        Group group = new Group();
        group.addActor(new MyTestActor());
        group.addActor(new SecondTestActor());
        group.setTransform(false);
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

        this.stage.draw();
        this.stage.act(Gdx.graphics.getDeltaTime());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose () {
        this.stage.dispose();
        Gdx.input.setInputProcessor(null);
    }

    /**
     * Sets the input listener to the screen.
     *
     * @param inputListener - the {@link InputListener} instance.
     */
    public void setInputListener(InputListener inputListener) {
        this.stage.addListener(inputListener);
        Gdx.input.setInputProcessor(this.stage);
    }

    /**
     * Fades out the screen.
     *
     * @param postAction - the post action to be executed after sequence.
     */
    public void fadeOutScreen(Runnable postAction) {
        this.stage.getRoot().getColor().a = 1;
        SequenceAction sequenceAction = new SequenceAction();
        sequenceAction.addAction(fadeOut(FADE_DURATION));
        sequenceAction.addAction(run(postAction));
        this.stage.getRoot().addAction(sequenceAction);
    }
}
