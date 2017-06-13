package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Zabawa implements Screen {
	private Stage stage;
	final Gra game;
	private MyActor aktor;

	public Zabawa (Gra game) {

		this.game = game;
		stage = new Stage(new ScreenViewport());
		aktor = new MyActor();
		Gdx.input.setInputProcessor(stage);
		stage.addActor(aktor);
	}

	public void resize (int width, int height) {
		// See below for what true means.
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void show() {

	}

	public void render (float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
	}

	public void dispose() {
		stage.dispose();
	}
}
