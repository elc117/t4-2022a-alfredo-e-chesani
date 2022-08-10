package com.mygdx.game.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
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
    Projectile p = null;
    float deathTime = -1;
    public Enemy(float x, float y, MainCharacter target){
        animator = new Animator();
        animator2 = new Animator();
        this.setXY(x, y);
        this.target = target;
        this.alive = true;
        this.hitBox = new Rectangle(this.x, this.y, 150, 300);
        this.projX = this.x;
        this.projY = this.y;
        animator.AddAnimation("fire.png", 13, 1f, "fire");
        animator.AddAnimation("enemyStand.png", 13, 1f, "stand");
        animator.AddAnimation("enemyDeath.png", 8, 2f, "death");
        animator2.AddAnimation("spell.png", 5, 0.3f, "spell");
        animator.StartAnimation("stand");
        animator2.StartAnimation("spell");
    }
    public boolean getAlive(){
        return alive;
    }
    public void fire(SpriteBatch batch){
        float tx = target.getX();
        float ty = target.getY() + target.h/2f; //mirando no peito
        float dx = tx - this.x;
        float dy = ty - (this.y+hitBox.height-80);

        if(p == null)
        {
            p = new Projectile(x, y + hitBox.height-80, new Vector2(dx,dy));
        }

        if(dx > 0){
            target.setHitDir(1);
        }
        else{
            target.setHitDir(-1);
        }
        
        if(p != null && p.hitBox.overlaps(target.hitBox)){
            setSound("Sounds/spell.wav");
            target.gotHit = true;
            p = null;
            animator.StartAnimation("fire");
        }
        else{
            for(Rectangle f : this.hitBoxes){
                if(p!= null && p.hitBox.overlaps(f)){
                    p = null;
                }
            }
        }
        TextureRegion frame_spell = animator2.UpdateFrame();
        if(p != null)
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
        batch.draw(frame, this.x, this.y, this.hitBox.width, this.hitBox.height);
        fire(batch);
    }
}
