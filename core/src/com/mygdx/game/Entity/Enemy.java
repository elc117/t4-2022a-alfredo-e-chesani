package com.mygdx.game.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.actions.DelegateAction;
import com.badlogic.gdx.Gdx;
import java.lang.Math;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.Input.Keys;
public class Enemy extends Entity{
    MainCharacter target;
    float projX;
    float projY;
    protected boolean alive;
    Animator animator2;
    float deathTime = -1;
    public Enemy(float x, float y, MainCharacter target){
        animator = new Animator();
        animator2 = new Animator();
        this.setXY(x, y);
        this.target = target;
        this.alive = true;
        this.hitBox = new Rectangle(this.x, this.y, 100, 100);
        this.projX = this.x;
        this.projY = this.y;
        animator.AddAnimation("_Fire.png", 13, 1f, "fire");
        animator.AddAnimation("_EnemyStand.png", 13, 1f, "stand");
        animator.AddAnimation("_EnemyDeath.png", 8, 2f, "death");
        animator2.AddAnimation("_Spell.png", 5, 0.3f, "spell");
        animator.StartAnimation("stand");
        animator2.StartAnimation("spell");
    }
    public boolean getAlive(){
        return alive;
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
            setSound("Sounds/spell.wav");
            target.gotHit = true;
            projX = this.x;
            projY = this.y + 100;
            animator.StartAnimation("fire");
        }
        TextureRegion frame_spell = animator2.UpdateFrame();
        p.update(batch, frame_spell);
    }

    public boolean death(){
        if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
            if(target.attack().overlaps(this.hitBox)){                
                deathTime = 0;
                animator.StartAnimation("death");
                return false;
            }
        }

        if(deathTime != -1)
        {
            deathTime+= Gdx.graphics.getDeltaTime();
        }

        if(deathTime < 1.9f)
        {
            return false;
        }

        return true;
    }

    public void update(SpriteBatch batch){
        if(death()){
            alive = false;
        }
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
