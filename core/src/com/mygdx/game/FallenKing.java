package com.mygdx.game;
import java.util.ArrayList;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Input.Keys;
import java.util.ArrayList;
import com.mygdx.game.Entity.*;
import com.mygdx.game.Stage.*;

public class FallenKing extends Game{

	public SpriteBatch batch;
	public MainCharacter player;

	@Override
	public void create ()
	{
		player = new MainCharacter();
		batch = new SpriteBatch();
		setScreen(new TitleScreen(batch, this));
	}

	@Override
	public void dispose (){
		batch.dispose();
	}
}
