package com.mygdx.game.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Gdx;
import java.lang.Math;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
public class Enemy extends Entity{
    MainCharacter target;
    float projX;
    float projY;
    Animator animator2;
    public Enemy(float x, float y, MainCharacter target){
        animator = new Animator();
        animator2 = new Animator();
        this.setXY(x, y);
        this.target = target;
        this.hitBox = new Rectangle(this.x, this.y, 100, 100);
        this.projX = this.x;
        this.projY = this.y;
        animator.AddAnimation("_Fire.png", 13, 1f, "fire");
        animator.AddAnimation("_Enemy_stand.png", 13, 1f, "stand");
        animator.StartAnimation("stand");
        animator2.AddAnimation("_Spell.png", 5, 0.3f, "spell");
        animator2.StartAnimation("spell");
    }

    public void fire(SpriteBatch batch){
        Projectile p = new Projectile(projX, projY);
        float tx = target.getX();
        float ty = target.getY();
        double dx = tx - projX;
        double dy = ty - projY;
        if(dx > 0)
            target.setHitDir(1);
        if(dx < 0)
            target.setHitDir(-1);
        projX += dx * Gdx.graphics.getDeltaTime();
        projY += dy * Gdx.graphics.getDeltaTime();
        if(p.hitBox.overlaps(target.hitBox)){
            target.gotHit = true;
            projX = this.x;
            projY = this.y + 100;
            animator.StartAnimation("fire");
        }
        TextureRegion frame_spell = animator2.UpdateFrame();
        p.update(batch, frame_spell);
    }

    public void update(SpriteBatch batch){
        TextureRegion frame = animator.UpdateFrame();
        if(this.x > target.x && !frame.isFlipX()){
            frame.flip(true, false);
        }
        if(this.x < target.x && frame.isFlipX()){
            frame.flip(true, false);
        }
        batch.draw(frame, this.x, this.y, 200, 300);
        fire(batch);
    }
}