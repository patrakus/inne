package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by Patryk on 2016-12-19.
 */

public class NinjaCharacter implements Disposable{
    private TextureAtlas ninjaGirlTexAtlas;
    private Animation<TextureAtlas.AtlasRegion> runningNinjaAnimation;
    private Animation<TextureAtlas.AtlasRegion> idleNinjaAnimation;
    private Animation<TextureAtlas.AtlasRegion> jumpingNinjaAnimation;
    private Animation<TextureAtlas.AtlasRegion> attackingNinjaAnimation;
    private Animation<TextureAtlas.AtlasRegion> jumpingAttackNinjaAnimation;
    private Animation<TextureAtlas.AtlasRegion> deadNinjaAnimation;
    private Array<TextureAtlas.AtlasRegion> runningFrames;
    private Array<TextureAtlas.AtlasRegion> idleFrames;
    private Array<TextureAtlas.AtlasRegion> jumpingFrames;
    private Array<TextureAtlas.AtlasRegion> attackFrames;
    private Array<TextureAtlas.AtlasRegion> jumpAttackFrames;
    private Array<TextureAtlas.AtlasRegion> deadFrames;

    private Body characterBody;
    private Sprite sprite;

    private float stateTime;
    private boolean flipped = false;
    private boolean jumping = false;
    public float characterSpeed = 20.0f;
    private float oldYPos = -1;
    private float oldXPos = -1;
    private boolean standing = true;


    public NinjaCharacter(World world)
    {

        createBody(world);
        ninjaGirlTexAtlas = new TextureAtlas("NinjaGirl.txt");

        runningFrames = ninjaGirlTexAtlas.findRegions("Run");
        idleFrames = ninjaGirlTexAtlas.findRegions("Idle");
        jumpingFrames = ninjaGirlTexAtlas.findRegions("Jump");
        attackFrames = ninjaGirlTexAtlas.findRegions("Attack");
        jumpAttackFrames = ninjaGirlTexAtlas.findRegions("Jump_Attack");
        deadFrames = ninjaGirlTexAtlas.findRegions("Dead");

        runningNinjaAnimation = new Animation<TextureAtlas.AtlasRegion>(0.1f, runningFrames);
        idleNinjaAnimation = new Animation<TextureAtlas.AtlasRegion>(0.1f, idleFrames);
        jumpingNinjaAnimation = new Animation<TextureAtlas.AtlasRegion>(0.1f, jumpingFrames);
        attackingNinjaAnimation = new Animation<TextureAtlas.AtlasRegion>(0.1f, attackFrames);
        jumpingAttackNinjaAnimation = new Animation<TextureAtlas.AtlasRegion>(0.1f, jumpAttackFrames);
        deadNinjaAnimation = new Animation<TextureAtlas.AtlasRegion>(0.1f, deadFrames);

        stateTime = 0f;

    }

    public void createBody(World world)
    {
        // Create our body definition
        BodyDef characterBodyDef = new BodyDef();
        // Set its world position
        characterBodyDef.position.set(new Vector2(22+2.5f, 6));
        characterBodyDef.type = BodyDef.BodyType.DynamicBody;


        // Create a body from the defintion and add it to the world
        characterBody = world.createBody(characterBodyDef);

        // Create a polygon shape
        PolygonShape groundBox = new PolygonShape();
        // Set the polygon shape as a box which is twice the size of our view port and 20 high
        // (setAsBox takes half-width and half-height as arguments)
        groundBox.setAsBox(2.5f,4); //TODO: na inny rozdzielczościach ekranu wielkość tego boxa może zrodzić problemy z pokryciem body a obrazka
        // Create a fixture from our polygon shape and add it to our ground body
        characterBody.createFixture(groundBox, 0.0f);
        characterBody.setUserData("Player");
        // Clean up after ourselves
        groundBox.dispose();
    }

    //TODO: dokonczyc sterowanie postacia

    public void draw(SpriteBatch batch, float offsetX, float offsetY, float knobXInPercent) // class logic
    {
        stateTime += Gdx.graphics.getDeltaTime();

        translateCharacter(offsetX, offsetY);

        if (sprite == null)
        {
            sprite = new Sprite(movement(knobXInPercent));
            sprite.setSize(5,8);
            //sprite.setScale(0.5f);
            //sprite.setSize(currentFrame.getRegionWidth() * 0.5f, currentFrame.getRegionHeight() * 0.5f);
            //sprite.setOrigin(currentFrame.getRegionWidth() * 0.25f, currentFrame.getRegionHeight() * 0.25f);
            //System.out.println("Sprite size x: " + sprite.getWidth());
            //System.out.println("Sprite size y: " + sprite.getHeight());
            //System.out.println("Sprite origin pos x: " + sprite.getOriginX());
            //System.out.println("Sprite origin pos y: " + sprite.getOriginY());
            //sprite.setSize(sprite.getWidth()/ 2, sprite.getHeight()/2);

        }
        else
        {
            sprite.setRegion(movement(knobXInPercent));
        }
        isSpriteFlipped(knobXInPercent);

        if (sprite.isFlipX() && flipped == false)
        {
            sprite.flip(true, false);
        }
        else if (!sprite.isFlipX() && flipped == true)
        {
            sprite.flip(true, false);
        }

        sprite.setPosition(characterBody.getPosition().x -2.5f + offsetX, characterBody.getPosition().y - 4 + offsetY);
        sprite.draw(batch);
    }

    private void translateCharacter(float offsetX, float offsetY)
    {
        Vector2 newPos = new Vector2(characterBody.getPosition().x + offsetX, characterBody.getPosition().y + offsetY);

        if (oldYPos == -1)
            oldYPos = newPos.y;
        else if(oldYPos == newPos.y)
            jumping = false;
        else
        {
            jumping = true;
            oldYPos = newPos.y;
        }

        if (oldXPos == -1)
        {
            oldXPos = newPos.x;
        }
        else if(oldXPos == newPos.x)
            standing = true;
        else
        {
            standing = false;
            oldXPos = newPos.x;
        }


        characterBody.setAwake(true);
        characterBody.setTransform(newPos, characterBody.getAngle());
    }

    private TextureRegion movement(float knobXInPercent)
    {
        if (knobXInPercent < -0.3f && !isJumping())
        {
            return runningNinjaAnimation.getKeyFrame(stateTime, true);
        }
        else if (knobXInPercent > 0.3f && !isJumping())
        {
            return runningNinjaAnimation.getKeyFrame(stateTime, true);
        }
        else if (isJumping())
        {
            return jumpingNinjaAnimation.getKeyFrame(stateTime,false);
        }
        else
        {
            return idleNinjaAnimation.getKeyFrame(stateTime, true);
        }
    }

    private void isSpriteFlipped(float knobXInPercent)
    {
        if (knobXInPercent < -0.3f)
        {
            flipped = true;
        }
        else
        {
            flipped = false;
        }
    }

    public void jump()
    {
        characterBody.applyLinearImpulse(0f,17f, characterBody.getPosition().x,characterBody.getPosition().y,true);
    }

    public boolean isJumping()
    {
        return jumping;
    }

    public Vector2 getBodyPosition()
    {
        return characterBody.getPosition();
    }

    public boolean isStandingInOnePlace()
    {
        return standing;
    }

    public boolean isPlayerDead()
    {
        return deadZone(-1f);
    }

    private boolean deadZone(float posX)
    {
        return getBodyPosition().x < posX;
    }

    @Override
    public void dispose() {
        ninjaGirlTexAtlas.dispose();
    }
}
