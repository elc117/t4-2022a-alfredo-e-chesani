package com.mygdx.game.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Input.Keys;
import java.util.ArrayList;

public class MainCharacter extends Entity{
    public MainCharacter(){
        this.sprite = new Texture("character_test.png");
        setXY(50, 300);
        this.hitBox = new Rectangle(this.x, this.y, 50, 100);
    }
    public void update(SpriteBatch batch, ArrayList<Floor> rects){
        batch.draw(sprite, this.x, this.y, 100, 100);
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