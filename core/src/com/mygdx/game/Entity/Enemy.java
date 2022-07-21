package com.mygdx.game.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Gdx;
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
        if(projX >  target.getX())
            projX -= 200 * Gdx.graphics.getDeltaTime();
        if(projX <  target.getX())
            projX += 200 * Gdx.graphics.getDeltaTime();
        if(projY >  target.getY())
            projY -= 200 * Gdx.graphics.getDeltaTime();
        if(projY <  target.getY())
            projY += 200 * Gdx.graphics.getDeltaTime();

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