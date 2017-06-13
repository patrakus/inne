package com.mygdx.game;

import com.badlogic.gdx.Game;

/**
 * Created by patryk on 2016-04-19.
 */
public class Gra extends Game {

    public void create() {
        this.setScreen(new Zabawa(this));
    }

    public void render() {
        super.render(); //important!
    }

    public void dispose() {

    }

}