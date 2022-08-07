package com.mygdx.game.Stage;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
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
import com.mygdx.game.Entity.Animator;

public class TitleScreen extends ScreenAdapter
{
    SpriteBatch batch;
    Game game;
    int width;
    Stage stage;
    int height;
    Animator background;
    Music theme;
    Vector2 buttonSize = new Vector2(250, 150);


    public TitleScreen(SpriteBatch batch, Game game)
    {
        this.game = game;
        this.batch = batch;
    }

    @Override
    public void show()
    {
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        stage = new Stage();
        background = new Animator();
        background.AddAnimation("menu.png", 4, 1.0f, "menu");
        background.StartAnimation("menu");
        theme = Gdx.audio.newMusic(Gdx.files.internal("menu.mp3"));
        theme.setLooping(true);
        theme.setVolume(0.1f);
        theme.play();

        Button play = createButton("play.pack", new Rectangle(500,150,200,150));
        Button sair = createButton("sair.pack", new Rectangle(500,50,200,150));

        play.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                theme.stop();
                game.setScreen(new GameScreen(batch, game));
            }
        });

        Gdx.input.setInputProcessor(stage);
        stage.addActor(play);
        stage.addActor(sair);
    }

    @Override
    public void render(float delta)
    {
		ScreenUtils.clear(0, 0, 0, 1);

        batch.begin();
        batch.draw(background.UpdateFrame(), 0, 0, width, height);
        batch.end();

        stage.act(delta);
        stage.draw();

    }
  
    @Override
    public void hide()
    {
        stage.dispose();
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

}
