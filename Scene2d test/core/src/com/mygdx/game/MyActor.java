package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by patryk on 2016-04-10.
 */
public class MyActor extends Actor {
    TextureRegion region;

    public MyActor () {
        region = new TextureRegion( new Texture("badlogic.jpg"));
    }
    @Override
    public void draw (Batch batch, float parentAlpha) {
        Color color = getColor();
        //batch.begin();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        //batch.end();
    }
}
