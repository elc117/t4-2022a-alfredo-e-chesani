package com.mygdx.game.Stage;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Entity.MainCharacter;

public class GameScreen extends ScreenAdapter
{
    MainCharacter player;
    OrthographicCamera camera;
    Music castleTheme;
    Stage currentStage;
	ArrayList<Stage> stages;
    SpriteBatch batch;
    int width;
    int height;
    
    public GameScreen(SpriteBatch batch)
    {
        this.batch = batch;
        this.player = new MainCharacter();
        stages = new ArrayList<Stage>();
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
    }

    @Override
    public void render(float delta)
    {
		ScreenUtils.clear(0, 0, 0, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        
		batch.begin();

		currentStage.update(batch);
		player.update(batch);

		batch.end();
    }

    //função a ser chamada pelos próprios stages
    public void updateStage(int index)
    {
        currentStage = stages.get(index);
    }
}
