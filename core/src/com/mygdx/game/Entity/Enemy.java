package com.mygdx.game.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Gdx;
import java.lang.Math;
public class Enemy extends Entity{
    MainCharacter target;
    float projX;
    float projY;
    public Enemy(float x, float y, MainCharacter target){
        this.setXY(x, y);
        this.target = target;
        this.sprite = new Texture("enemy.png");
        this.hitBox = new Rectangle(this.x, this.y, 100, 100);
        this.projX = this.x;
        this.projY = this.y;
    }

    public void fire(SpriteBatch batch){
        Projectile p = new Projectile(projX, projY, target);
        float tx = target.getX();
        float ty = target.getY();

        double dx = tx - projX;
        double dy = ty - projY;
        if(projX > tx)
            projX += 2*dx * Gdx.graphics.getDeltaTime();
        if(projX <  tx)
            projX += 2*dx * Gdx.graphics.getDeltaTime();
        if(projY >  ty)
            projY += 2*dy * Gdx.graphics.getDeltaTime();
        if(projY <  ty)
            projY += 2*dy * Gdx.graphics.getDeltaTime();

        if(p.hitBox.overlaps(target.hitBox)){
            projX = this.x;
            projY = this.y + 100;
        }
        p.update(batch);
    }

    public void update(SpriteBatch batch){
        batch.draw(this.sprite, this.x, this.y, 100, 200);
        fire(batch);
    }
}