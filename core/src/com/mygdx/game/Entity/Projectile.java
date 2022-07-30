package com.mygdx.game.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
public class Projectile extends Entity{
    public Projectile(float x, float y){
        setXY(x, y);
        this.hitBox = new Rectangle(this.x, this.y, 50, 50);
        
    }

    public void update(SpriteBatch batch, TextureRegion frame){
        batch.draw(frame, this.x, this.y, this.hitBox.width, this.hitBox.height);
        this.hitBox.x = this.x;
        this.hitBox.y = this.y;
    }
}