package com.alta_v2.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

/**
 * The game screenshot that based on the tiled map.
 */
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class TiledMapScreen extends ScreenAdapter {

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture texture;

    @Setter
    private int color;

    /**
     * {@inheritDoc}
     */
    @Override
    public void show () {
        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.camera.position.x  = this.camera.viewportWidth / 2f;
        this.camera.position.y  = this.camera.viewportHeight / 2f;

        this.batch = new SpriteBatch();
        this.texture = new Texture(Gdx.files.internal("badlogic.jpg"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.batch.begin();
        this.batch.draw(this.texture, 10, this.color);
        this.camera.update();
        this.batch.end();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose () {
        this.batch.dispose();
        this.texture.dispose();
    }

}
