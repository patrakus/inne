package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Patryk on 2017-01-22.
 */

public class GameCore extends Game {

    public SpriteBatch batch;
    public BitmapFont font;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        this.setScreen(new GameStart(this));
    }

    @Override
    public void dispose()
    {
        super.dispose();
        batch.dispose();
        font.dispose();
    }
}
