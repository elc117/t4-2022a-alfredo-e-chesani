package com.mygdx.game.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;

public class Projectile extends Entity{

    float targetX;
    float targetY;
    public Projectile(float x, float y, MainCharacter target){
        setXY(x, y);
        this.targetX = target.getX();
        this.targetY = target.getY();
        this.sprite = new Texture("projectile.png");
        this.hitBox = new Rectangle(this.x, this.y, 50, 50);
    }

    public void update(SpriteBatch batch){
        batch.draw(this.sprite, this.x, this.y, 50, 50);
        this.hitBox.x = this.x;
        this.hitBox.y = this.y;
    }
}