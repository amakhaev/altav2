package com.alta_v2.game.screen;

import com.alta_v2.game.MyTestActor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;

/**
 * The screen that based on the tiled map.
 */
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class TiledMapScreen extends ScreenAdapter {

    private Stage stage;

    /**
     * {@inheritDoc}
     */
    @Override
    public void show () {
        OrthographicCamera camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.x  = camera.viewportWidth / 2f;
        camera.position.y  = camera.viewportHeight / 2f;

        this.stage = new Stage(new ScreenViewport(camera));

        this.stage.addActor(new MyTestActor());
        this.stage.getRoot().getColor().a = 0f;
        this.stage.addAction(Actions.fadeIn(3f));
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
    }

    public void fadeOutScreen(Runnable postAction) {
        this.stage.getRoot().getColor().a = 1;
        SequenceAction sequenceAction = new SequenceAction();
        sequenceAction.addAction(fadeOut(0.5f));
        sequenceAction.addAction(run(postAction));
        this.stage.getRoot().addAction(sequenceAction);
    }
}
