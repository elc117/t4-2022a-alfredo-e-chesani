package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Entity{
    protected Boolean isColliding;
    protected float x;
    protected float y;
    Texture sprite;
    public void setXY(float x, float y){
        this.x = x;
        this.y = y;
    }
    public void update(SpriteBatch batch ){
        batch.draw(sprite, this.x, this.y, 50, 50);
        if(Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) 
      		this.x -= 200 * Gdx.graphics.getDeltaTime();
   		if(Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) 
      		this.x += 200 * Gdx.graphics.getDeltaTime();
   		if(Gdx.input.isKeyPressed(Keys.DPAD_UP)) 
      		this.y += 200 * Gdx.graphics.getDeltaTime();
   		if(Gdx.input.isKeyPressed(Keys.DPAD_DOWN)) 
      		this.y -= 200 * Gdx.graphics.getDeltaTime();
    }
}

public class Character extends Entity{
    puclic Character(){
        this.sprite = new Texture("Colour1/NoOutline/120x80_PNGSheets/_CrouchTransition.png");
        setXY(0, 0);
    }
}