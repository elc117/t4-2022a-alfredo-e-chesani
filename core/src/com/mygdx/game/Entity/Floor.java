package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Input.Keys;

public class Floor extends Entity{
    public Floor(){
        setXY(50, 0);
        this.hitBox = new Rectangle(this.x, this.y, 2000, 50);
        this.sprite = new Texture("floor.jpg");
    }
    void colider(Rectangle floor[]){

    }

    public void update(SpriteBatch batch, Rectangle rects[]){
        batch.draw(sprite, this.x, this.y, 2000, 50);
    }
}