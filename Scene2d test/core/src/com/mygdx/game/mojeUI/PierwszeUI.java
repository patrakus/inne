package com.mygdx.game.mojeUI;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * Created by patryk on 2016-04-20.
 */
public class PierwszeUI implements ApplicationListener {

    private Stage stage;
    private Table root;
    private Table innerTable;
    private Skin skin;

    @Override
    public void create() {

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        root = new Table();
        innerTable = new Table();
        skin = new Skin();

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));

        skin.add("default", new BitmapFont());

        // Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.ORANGE);
        textButtonStyle.down = skin.newDrawable("white", Color.PINK);
        textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

        root.setFillParent(true);
        //root.debug();
        stage.addActor(root);
        stage.addActor(innerTable);
        innerTable.setSize(260, 195);
        innerTable.setPosition(190, 142);
        innerTable.debug();

        final TextButton button = new TextButton("Click me!", skin);
        innerTable.add(button).expand(100,100);
        innerTable.row();
        final TextButton button2 = new TextButton("Click me2!", skin);
        innerTable.add(button2);

        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Clicked! Is checked: " + button.isChecked());
                button.setText("Good job!");

            }
        });

        button2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Clicked! Is checked: " + button2.isChecked());
                button2.setText("Good job!");

            }
        });
    }

    @Override
    public void resize(int width, int height) {

        stage.getViewport().update(width, height, true);
    }

    @Override
    public void render() {

        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

        stage.dispose();
        skin.dispose();
    }
}
