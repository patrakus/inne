package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 * Created by Patryk on 2017-01-23.
 */

public class BoxTrigger implements ContactListener {

    private final GameCore game;
    private final PlatformerGame platformerGame;

    public BoxTrigger(final GameCore game, final PlatformerGame platformerGame)
    {
        this.game = game;
        this.platformerGame = platformerGame;
    }

    @Override
    public void beginContact(Contact contact) {


        if (contact.getFixtureB().isSensor()) {
            //game.setScreen(new GameOver(game, platformerGame.viewport));
            //platformerGame.dispose();
            Gdx.app.exit();
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
