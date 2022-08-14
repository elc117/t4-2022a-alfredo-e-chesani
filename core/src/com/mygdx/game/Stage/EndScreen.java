package com.mygdx.game.Stage;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class EndScreen extends ScreenAdapter
{
    SpriteBatch batch;
    Game game;
    int width;
    int height;
    Texture background;
    Stage stage;
    Music theme;
    Vector2 buttonSize = new Vector2(250, 150);
    OrthographicCamera camera;
    Viewport viewport;

    public EndScreen(SpriteBatch batch, Game game)
    {
        this.game = game;
        this.batch = batch;
    }

    @Override
    public void show()
    {
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        theme = Gdx.audio.newMusic(Gdx.files.internal("menu.mp3"));
        background = new Texture("endGame.png");

        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
        viewport = new FitViewport(width, height, camera);

        stage = new Stage();
        Button voltar = createButton("voltar.pack", new Rectangle(100,20,200,150));

        voltar.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                theme.stop();
                game.setScreen(new TitleScreen(batch, game));
            }
        });

        stage.addActor(voltar);
    }

    @Override
    public void render(float delta)
    {
        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

		ScreenUtils.clear(0, 0, 0, 1);

        batch.draw(background, 0, 0, width, height);
        batch.end();

        Gdx.input.setInputProcessor(stage);
        stage.act(delta);
        stage.draw();
    }
  
    @Override
    public void hide()
    {

    }

    private Button createButton(String pack, Rectangle bounds)
    {
        Skin skin = new Skin(new TextureAtlas(pack));;
        ButtonStyle style = new ButtonStyle();

        String subPack = pack.toLowerCase();
        
        style.up = skin.getDrawable(subPack.replace(".pack", ".up"));
        style.down = skin.getDrawable(subPack.replace(".pack", ".down"));
        style.over = skin.getDrawable(subPack.replace(".pack", ".checked"));
    
        Button button = new Button(style);
        button.setBounds(width/2 - bounds.width/2, bounds.y, bounds.width, bounds.height);

        return button;
    }

    @Override
    public void resize(int widthi, int heighti) 
    {
        viewport.update(widthi, heighti);
        camera.update();
    }

}
