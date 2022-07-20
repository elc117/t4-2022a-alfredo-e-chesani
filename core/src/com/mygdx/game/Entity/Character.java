package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Input.Keys;

public class Character extends Entity{

    public Character(){
        this.sprite = new Texture("Colour1/NoOutline/120x80_PNGSheets/_CrouchTransition.png");
        setXY(50, 300);
        this.hitBox = new Rectangle(this.x, this.y, 50, 50);
    }
    public void update(SpriteBatch batch, Rectangle rects[]){
        batch.draw(sprite, this.x, this.y, 200, 200);
        this.hitBox.x = this.x;
        this.hitBox.y = this.y;
        if(speed > -200){
            speed-=1;
        }
        this.colider(rects);
        this.y += speed;
        if(Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) 
      		this.x -= 500 * Gdx.graphics.getDeltaTime();
   		if(Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) 
      		this.x += 500 * Gdx.graphics.getDeltaTime();
   		if(Gdx.input.isKeyPressed(Keys.DPAD_UP))
      		speed = 20;
    }
}