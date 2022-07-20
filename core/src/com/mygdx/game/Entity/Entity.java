package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Intersector;

public class Entity{
    float fallSpeed = 0;
    public float mass = 1;
    public Rectangle hitBox;

    //protected Boolean isColliding;
    protected float x;
    protected float y;
    Texture sprite;
    public void setXY(float x, float y){
        this.x = x;
        this.y = y;
    }
    public Rectangle getHitBox() {
        return hitBox;
    }

    Rectangle colider(Rectangle floor[]){
        for(Rectangle rect: floor){
            if(this.hitBox.overlaps(rect)){
                return rect;
            }
        }
        return null;
    }

    
}

