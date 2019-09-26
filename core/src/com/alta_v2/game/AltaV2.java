package com.alta_v2.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class AltaV2 extends ApplicationAdapter {

	private TiledMap tiledMap;
	private AssetManager assetManager;
	private TiledMapRenderer tiledMapRenderer;
	private OrthographicCamera camera;
	
	@Override
	public void create () {
		this.assetManager = new AssetManager();
		this.assetManager.setLoader(TiledMap.class, new TmxMapLoader());
		this.assetManager.load("maps/test/map.tmx", TiledMap.class);
		this.assetManager.load("maps/map.tmx", TiledMap.class);
		this.assetManager.finishLoading();

		this.tiledMap = this.assetManager.get("maps/test/map.tmx", TiledMap.class);
		this.tiledMapRenderer = new OrthogonalTiledMapRenderer(this.tiledMap);

		this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.camera.position.x  = this.camera.viewportWidth / 2f;
		this.camera.position.y  = this.camera.viewportHeight / 2f;

		Gdx.input.setInputProcessor(new MyInputProcessor());
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.camera.update();
		this.tiledMapRenderer.setView(this.camera);
		this.tiledMapRenderer.render();
	}
	
	@Override
	public void dispose () {
		this.tiledMap.dispose();
		this.assetManager.dispose();
	}
}
