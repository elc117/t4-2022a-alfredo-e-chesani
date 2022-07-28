package com.mygdx.game.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Input.Keys;
import java.util.ArrayList; 

public class Entity{
    float fallSpeed = 0;
    public float mass = 1;
    public Rectangle hitBox;
    protected static ArrayList<Rectangle> hitBoxes = new ArrayList<Rectangle>();
    Animator animator;
    //protected Boolean isColliding;
    protected float x;
    protected float y;
    Texture sprite;
    public void setXY(float x, float y){
        this.x = x;
        this.y = y;
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public Rectangle getHitBox(){
        return hitBox;
    }
    Rectangle GetCollision(){
        for(Rectangle rect: hitBoxes){
            if(this.hitBox.overlaps(rect))
            {
                return rect;
            } 
        }
        return null;
    }
}
