package com.mygdx.game;
import java.util.ArrayList;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Stage.Level1;
import com.badlogic.gdx.Input.Keys;
import java.util.ArrayList;
import com.mygdx.game.Entity.*;
import com.mygdx.game.Stage.*;

public class FallenKing extends ApplicationAdapter{
	final int n_floor = 4;
	SpriteBatch batch;
	Texture img;
	MainCharacter player;
	Level1 l1;

	@Override
	public void create (){
		player = new MainCharacter();
		batch = new SpriteBatch();
		l1 = new Level1(player);
	}

	
	@Override
	public void render (){
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		l1.update(batch);

		player.update(batch);
		batch.end();
	}
	
	@Override
	public void dispose (){
		batch.dispose();
		img.dispose();
	}
}
