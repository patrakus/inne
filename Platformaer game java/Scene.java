package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by Patryk on 2017-01-19.
 */

public class Scene implements Disposable{
    private TextureAtlas gameObjects;
    private TextureAtlas terrainTiles;
    private Array<Sprite> terrainSpriteTiles;
    private Array<Vector2> terrainTilesPosition;
    private Array<Integer> terrainTileIndices;
    private World world;

    public Scene( World world)
    {
        gameObjects = new TextureAtlas("Objects.txt");
        terrainTiles = new TextureAtlas("Tiles.txt");

        terrainSpriteTiles = terrainTiles.createSprites();

        for (Sprite terrainTile: terrainSpriteTiles) {
            terrainTile.setSize(5,5);
        }

        terrainTilesPosition = new Array<Vector2>();
        terrainTileIndices = new Array<Integer>();

        this.world = world;

        setupScene();
        addEndPointOfMap(30, 80, 270,10);

    }

    private void setupScene() //TODO: hardcoded map
    {

        // wysepka startowa
        createIsland(10,1, 0, 0);
        createStaticLand(10,1, 0, 0);

        // wysepka wyższa
        createIsland(10,3, 80, 0);
        createStaticLand(10,3, 80, 0);

        // wysepka
        createIsland(8,1, 130, 0);
        createStaticLand(8,1, 130, 0);

        // wysepka
        createIsland(8,2, 170, 0);
        createStaticLand(8,2, 170, 0);

        // wysepka
        createIsland(3,3, 210, 0);
        createStaticLand(3,3, 210, 0);

        // wysepka
        createIsland(8,1, 225, 0);
        createStaticLand(8,1, 225, 0);

        // sciana
        //createIsland(1,3, 30, 7);
        //createStaticLand(1,3, 30, 7);
    }

    private void addEndPointOfMap(float width, float height, float posX, float posY)
    {
        width /= 2f;
        height /= 2f;

        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(new Vector2(posX , posY ));

        Body body = world.createBody(groundBodyDef);



        PolygonShape box = new PolygonShape();
        box.setAsBox(width, height);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.isSensor = true;
        fixtureDef.shape = box;
        body.createFixture(fixtureDef);
        box.dispose();

        body.setUserData("EndPoint");
    }

    private void createIsland(int width, int height, float posX, float posY)
    {
        for(int i = 0; i < width; i++ )
        {
            for(int j = 0; j < height; j++ )
            {
                terrainTilesPosition.add(new Vector2(posX + 5 * i,posY + 5*  j));

                if (i == 0)
                {
                    terrainTileIndices.add(4);
                }
                else if (i == width)
                {
                    terrainTileIndices.add(4);
                }
                else
                {
                    terrainTileIndices.add(4);
                }
            }
        }
    }

    private void createStaticLand(int width, int height, float posX, float posY)
    {
        //float size = 5 * width * height;

        // Create our body definition
        BodyDef groundBodyDef = new BodyDef();
        // Set its world position
        groundBodyDef.position.set(new Vector2(posX + (width * 5) / 2f, posY + (height * 5) / 2f));// TODO" dlaczego y nie trzeba dzielić przez 2?

        // Create a body from the defintion and add it to the world
        Body groundBody = world.createBody(groundBodyDef);

        // Create a polygon shape
        PolygonShape groundBox = new PolygonShape();
        // Set the polygon shape as a box which is twice the size of our view port and 20 high
        // (setAsBox takes half-width and half-height as arguments)
        groundBox.setAsBox((width * 5) / 2f, (height * 5) / 2f);
        // Create a fixture from our polygon shape and add it to our ground body
        groundBody.createFixture(groundBox, 0.0f);
        // Clean up after ourselves
        groundBox.dispose();
    }

    public void draw(Batch batch)
    {
        for (int i = 0; i < terrainTilesPosition.size; i++)
        {
            int tilePointer = terrainTileIndices.get(i);
            float posX = terrainTilesPosition.get(i).x;
            float posY = terrainTilesPosition.get(i).y;

            terrainSpriteTiles.get(tilePointer).setPosition(posX, posY);
            terrainSpriteTiles.get(tilePointer).draw(batch);
        }
    }

    @Override
    public void dispose()
    {
        gameObjects.dispose();
        terrainTiles.dispose();
    }
}
