package com.mygdx.game.Stage;
import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Entity.King;
import com.mygdx.game.Entity.MainCharacter;

public class GameScreen extends ScreenAdapter
{
    MainCharacter player;
    OrthographicCamera camera;
    Game game;
    Music castleTheme;
    public float zoom = 2;
    Texture pause;

    com.mygdx.game.Stage.Stage currentStage;
    boolean paused = false;
	ArrayList<com.mygdx.game.Stage.Stage> stages;

    SpriteBatch batch;
    int width;
    int height;
    Viewport viewport;
    
    public GameScreen(SpriteBatch batch, Game game)
    {
        this.batch = batch;
        this.player = new MainCharacter();
        this.game = game;

        stages = new ArrayList<com.mygdx.game.Stage.Stage>();
        
        pause = new Texture(Gdx.files.internal("pause.png"));
    }

    @Override
    public void show()
    {
        stages.add(new Level1(this));
        stages.add(new Level2(this));
        castleTheme = Gdx.audio.newMusic(Gdx.files.internal("castle.mp3"));
        //      stages.add(new Level3(player));
        
        //level inicial
        castleTheme.play();
        currentStage = stages.get(0);
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
        camera.zoom = this.zoom;
        viewport = new FitViewport(width, height, camera);
    }
    public float getZoom(){
        return this.zoom;
    }

    @Override
    public void render(float delta)
    {
        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        if(Gdx.input.isKeyJustPressed(Keys.ESCAPE))
        {
            paused = (paused == false);
        }
        
        if(paused)
        {
            int x = width/2 - pause.getWidth()/2;
            int y = height/2 - pause.getHeight()/2;
            batch.draw(pause, x, y);
        }
        else
        {
            ScreenUtils.clear(0, 0, 0, 1);

            currentStage.update(batch);
            player.update(batch);
        }
        batch.end();
    }

    @Override
    public void resize(int widthi, int heighti) 
    {
        viewport.update(widthi, heighti);
        camera.update();
    }

    //função a ser chamada pelos próprios stages
    public void updateStage(int index)
    {
        currentStage = stages.get(index);
    }


}

