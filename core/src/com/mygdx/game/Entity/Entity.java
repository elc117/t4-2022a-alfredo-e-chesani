package com.mygdx.game.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Input.Keys;
import java.util.ArrayList; 

public class Entity{
    float speed;
    public Rectangle hitBox;
    //protected Boolean isColliding;
    protected float x;
    protected float y;
    Texture sprite;
    public void setXY(float x, float y){
        this.x = x;
        this.y = y;
    }
    public Rectangle getHitBox(){
        return hitBox;
    }
    void colider(ArrayList<Floor> rects){
        for(Floor rect: rects){
            if(this.hitBox.overlaps(rect.getHitBox())){
                if(!Gdx.input.isKeyPressed(Keys.DPAD_UP))
                    speed = 0;
            }
        }
    }
}
