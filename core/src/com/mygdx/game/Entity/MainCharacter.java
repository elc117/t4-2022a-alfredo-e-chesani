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
    protected boolean walking = false;
    boolean flip = false;
    protected int HitDir;
    private double deltaImpact = 400;
    private double impactCooldown = 8;

    Animator animator;

    public MainCharacter()
    {
        animator = new Animator();
        animator.AddAnimation("_CrouchWalk.png", 9, 0.6f, "walk");
        animator.AddAnimation("_Jump.png", 3, 0.3f, "jump");
        animator.AddAnimation("_Stand.png", 3, 2f, "stand");
        animator.StartAnimation("stand");
        
        setXY(50, 200);
        this.hitBox = new Rectangle(this.x, this.y, 70, 100);
        mass = 40;
    }
    public void setHitDir(int dir)
    {
        this.HitDir = dir;
    }

    //agora apenas aumenta e diminui a velocidade do impacto
    public void impacto()
    {
        if(gotHit){
            System.out.println(HitDir);
            gotHit = false;
            hitSpeed = (int)(deltaImpact);
            if(fallSpeed < 0)
                fallSpeed += 4 * deltaImpact;
            return;
        }   
        
        if(hitSpeed > 0){
            hitSpeed -= impactCooldown;
            return;
        }
        hitSpeed = 0;
        HitDir = 0;
    }

    public void move()
    {
        float futureX = 0;
        float futureY = 0;
    
        if(Gdx.input.isKeyPressed(Keys.DPAD_LEFT) && hitSpeed <= 0) 
        {
            futureX -= moveSpeed * Gdx.graphics.getDeltaTime();
            flip = true;
            walking = true;
        }
        else if(Gdx.input.isKeyPressed(Keys.DPAD_RIGHT) && hitSpeed <= 0) 
        {
            futureX += moveSpeed * Gdx.graphics.getDeltaTime();
            walking = true;
            flip = false;
        }
        else{
            walking = false;
        }
        if(Gdx.input.isKeyJustPressed(Keys.DPAD_UP) && !isFalling && hitSpeed <= 0)
            fallSpeed = 1500;

        futureY += fallSpeed*Gdx.graphics.getDeltaTime();
        //x movido com base no impacto
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


        if(isFalling || fallSpeed > 1)
        {
            animator.StartAnimation("jump");
        }
        else if(walking)
        {
            animator.StartAnimation("walk");
        }
        else{
            animator.StartAnimation("stand");
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

        batch.draw(frame, this.x, this.y, this.hitBox.width, this.hitBox.height);
    }
}