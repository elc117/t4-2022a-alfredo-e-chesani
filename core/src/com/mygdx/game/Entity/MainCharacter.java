package com.mygdx.game.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Input.Keys;
import java.util.ArrayList;

public class MainCharacter extends Entity{

    public float moveSpeed = 600;
    public boolean gotHit = false;
    public int hitSpeed = 0;
    protected boolean isFalling = false;

    boolean flip = false;
    protected int HitDir;
    private double deltaImpact = 500;

    Animator animator;

    public MainCharacter()
    {
        animator = new Animator();
        animator.AddAnimation("_CrouchWalk.png", 8, 0.6f, "walk");
        animator.AddAnimation("_Jump.png", 3, 0.3f, "jump");
        animator.StartAnimation("walk");

        setXY(50, 200);
        this.hitBox = new Rectangle(this.x, this.y, 20, 40);
        mass = 40;
    }
    public void setHitDir(int dir)
    {
        this.HitDir = dir;
    }

    public void impacto()
    {
        if(gotHit)
        {
            gotHit = false;
            hitSpeed = (int)(deltaImpact);
            fallSpeed += 800;
            return;
        }   
        
        if(hitSpeed > 0)
        {
            hitSpeed -= 20;
            return;
        }

        hitSpeed = 0;
        HitDir = 0;
    }

    public void move()
    {
        float futureX = 0;
        float futureY = 0;
    
        if(Gdx.input.isKeyPressed(Keys.DPAD_LEFT) && !gotHit) 
        {
            futureX -= moveSpeed * Gdx.graphics.getDeltaTime();
            flip = true;
        }
        if(Gdx.input.isKeyPressed(Keys.DPAD_RIGHT) && !gotHit) 
        {
            futureX += moveSpeed * Gdx.graphics.getDeltaTime();
            flip = false;
        }

        if(Gdx.input.isKeyJustPressed(Keys.DPAD_UP) && !isFalling && !gotHit)
            fallSpeed = 1500;

        futureY += fallSpeed*Gdx.graphics.getDeltaTime();
        futureX += hitSpeed*HitDir*Gdx.graphics.getDeltaTime();
        this.hitBox.x += futureX;
        Rectangle collided = this.GetCollision();

        if(collided != null)
        {
            this.hitBox.x -= futureX;
        }

        this.hitBox.y += futureY;
        collided = this.GetCollision();
        
        if(collided != null)
        {
            this.hitBox.y -= futureY;
            isFalling = false;

            if(fallSpeed < 0)
            {
                hitBox.y = collided.y + collided.height;
            }

            fallSpeed = 0;
        }
        else
        {
            isFalling = true;
        }


        y = hitBox.y;
        x = hitBox.x;
    }

    public void update(SpriteBatch batch)
    {
        impacto();
        move();


        if(fallSpeed > 1)
        {
            animator.StartAnimation("jump");
        }
        else
        {
            animator.StartAnimation("walk");
        }

        if(fallSpeed > -2000)
        {
            fallSpeed -= 2*mass;
        }
        
        TextureRegion frame = animator.UpdateFrame();

        if(flip ^ frame.isFlipX())
        {
            frame.flip(true, false);
        }

        batch.draw(frame, this.x, this.y, 240, 360);
    }
}