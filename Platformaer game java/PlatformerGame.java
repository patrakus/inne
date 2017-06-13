package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sun.org.apache.xpath.internal.operations.String;

public class PlatformerGame implements Screen {

	private SpriteBatch batch;
	private NinjaCharacter ninjaGirl;
	private World world;
	private Stage stage;
	private Touchpad touchpad;
	private Touchpad.TouchpadStyle touchpadStyle;
	private Skin touchpadSkin;
	private Skin buttonSkin;
	private Button button;
	private Drawable buttonUp;
	private Drawable buttonDown;
	private Button.ButtonStyle buttonStyle;
	private Drawable touchBackground;
	private Drawable touchKnob;
	public final Viewport viewport;
	private final GameCore game;


	private Box2DDebugRenderer debugRenderer;

	private final float STEP_TIME = 1f / 60;
	private final int VELOCITY_ITERATIONS = 6;
	private final int POSITION_ITERATIONS = 2;

	float accumulator = 0;
	Scene myScene;
	

	public PlatformerGame ( final Viewport viewport, final GameCore game) {

		this.viewport = viewport;
		this.game = game;


		initBasicComponents();
		//createViewport();
		createUIStage();
		createUIComponents();
		setUIPosition();
		
		stage.addActor(touchpad);
		stage.addActor(button);
		Gdx.input.setInputProcessor(stage);


		//createGround();

		//System.out.println(viewport.getWorldWidth());
		//System.out.println(viewport.getScreenWidth());
		//System.out.println(viewport.getScreenX());
	}
	
	private void initBasicComponents()
	{
		Box2D.init();
		world = new World(new Vector2(0, -10), true);
		//debugRenderer = new Box2DDebugRenderer();//TODO
		world.setContactListener( new BoxTrigger(game, this));

		ninjaGirl = new NinjaCharacter(world);
		batch = new SpriteBatch();

		myScene = new Scene(world);
	}

	private void createUIStage()
	{
		stage = new Stage(viewport, batch);
	}

	private void createUIComponents()
	{
		touchpadSkin = new Skin();
		touchpadSkin.add("touchBackground", new Texture("touchBackground.png"));
		touchpadSkin.add("touchKnob", new Texture("touchKnob.png"));

		touchpadStyle = new Touchpad.TouchpadStyle();
		touchBackground = touchpadSkin.getDrawable("touchBackground");
		touchKnob = touchpadSkin.getDrawable("touchKnob");
		touchpadStyle.background = touchBackground;
		touchpadStyle.knob = touchKnob;
		touchpadStyle.knob.setMinHeight(2);
		touchpadStyle.knob.setMinWidth(2);

		touchpad = new Touchpad(0.25f, touchpadStyle);

		//touchpad.k


		buttonSkin = new Skin();
		buttonSkin.add("jumpButtonUp", new Texture("jumpButtonUp.png"));
		buttonSkin.add("jumpButtonDown", new Texture("jumpButtonDown.png"));

		buttonStyle = new Button.ButtonStyle();
		buttonUp = buttonSkin.getDrawable("jumpButtonUp");
		buttonDown = buttonSkin.getDrawable("jumpButtonDown");
		buttonStyle.up = buttonUp;
		buttonStyle.down = buttonDown;
		button = new Button(buttonStyle);

	}

	private void setUIPosition()
	{
		//touchpad.setBounds(viewport.getWorldWidth() * 0.01f, viewport.getWorldHeight() * 0.01f, 3, 3);
		touchpad.setBounds( 1, 3, 10, 10);
		touchpad.setSize(10,10);
		//touchpad.setDebug(true);//TODO
		button.setBounds(37, 3f, 6, 6);
	}

	private void createGround()
	{
		// Create our body definition
		BodyDef groundBodyDef = new BodyDef();
		// Set its world position
		groundBodyDef.position.set(new Vector2(viewport.getWorldWidth()/2, 1));

		// Create a body from the defintion and add it to the world
		Body groundBody = world.createBody(groundBodyDef);

		// Create a polygon shape
		PolygonShape groundBox = new PolygonShape();
		// Set the polygon shape as a box which is twice the size of our view port and 20 high
		// (setAsBox takes half-width and half-height as arguments)
		groundBox.setAsBox(viewport.getWorldWidth()/2, 1f);
		// Create a fixture from our polygon shape and add it to our ground body
		groundBody.createFixture(groundBox, 0.0f);
		// Clean up after ourselves
		groundBox.dispose();
	}

	private void stepWorld() {
		float delta = Gdx.graphics.getDeltaTime();

		accumulator += Math.min(delta, 0.25f);

		if (accumulator >= STEP_TIME) {
			accumulator -= STEP_TIME;

			world.step(STEP_TIME, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
		}
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		stepWorld();

		float offsetX = ninjaGirl.characterSpeed * Gdx.graphics.getDeltaTime() * touchpad.getKnobPercentX();

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix( viewport.getCamera().combined);
		batch.begin();
		myScene.draw(batch);
		ninjaGirl.draw(batch, offsetX, 0f, touchpad.getKnobPercentX());


		if (button.isPressed() && !ninjaGirl.isJumping())
		{
			ninjaGirl.jump();
		}

		batch.end();

		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();

		if (!ninjaGirl.isStandingInOnePlace())
		{
			button.setPosition(button.getX() + offsetX, button.getY());
			touchpad.setPosition(touchpad.getX() + offsetX, touchpad.getY());

			viewport.getCamera().translate(offsetX, 0f, 0f);
			viewport.getCamera().update();
		}

		//debugRenderer.render(world, viewport.getCamera().combined);//TODO
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
	public void dispose () {
		batch.dispose();
		world.dispose();
		stage.dispose();
		myScene.dispose();
		ninjaGirl.dispose();
	}
}