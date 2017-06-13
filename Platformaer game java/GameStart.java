package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.GL20;

/**
 * Created by Patryk on 2017-01-22.
 */

public class GameStart implements Screen {

    final GameCore game;
    private Viewport viewport;


    public GameStart(final GameCore game)
    {
        this.game = game;

        createViewport();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.font.draw(game.batch, "Tap Anywhere to begin", Gdx.graphics.getWidth() / 2,Gdx.graphics.getHeight() / 2);
        game.batch.end();

        if (Gdx.input.isTouched())
        {
            game.setScreen(new PlatformerGame( viewport, game));
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
    public void dispose() {

    }

    private void createViewport()
    {
        float aspectRatio = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
        OrthographicCamera camera = new OrthographicCamera();
        //camera.setToOrtho(false, 25, 25 * aspectRatio);
        //camera.position.set(camera.viewportWidth/2f, camera.viewportHeight/2f, 0f);
        //camera.update();
        viewport = new FitViewport(25 * aspectRatio, 25, camera);
        //viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        //viewport.setWorldSize();
    }
}
