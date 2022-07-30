package com.mygdx.game.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class Entity{
    float fallSpeed = 0;
    public float mass = 1;
    public Rectangle hitBox;
    protected static ArrayList<Rectangle> hitBoxes = new ArrayList<Rectangle>();
    Animator animator;
    Music sound;
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
    public void setSound(String path){
        this.sound = Gdx.audio.newMusic(Gdx.files.internal(path));
        this.sound.play();
    }
}
