package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Input.Keys;

public class FallenKing extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Character player;
	Floor floor;
	Rectangle rectVet[];
	@Override
	public void create (){
		player = new Character();
		batch = new SpriteBatch();
		floor = new Floor();
		rectVet = new Rectangle[1];
		rectVet[0] = floor.getHitBox();
	}

	
	@Override
	public void render (){
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		floor.update(batch, rectVet);
		player.update(batch, rectVet);
		batch.end();
	}
	
	@Override
	public void dispose (){
		batch.dispose();
		img.dispose();
	}
}
