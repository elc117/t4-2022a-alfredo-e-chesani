package com.mygdx.game.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Input.Keys;
import java.util.ArrayList;

public class MainCharacter extends Entity{

    public float moveSpeed = 800;
    protected boolean isFalling = false;
    Animator animator;

    public MainCharacter()
    {
        this.sprite = new Texture("character_test.png");
        setXY(50, 300);
        this.hitBox = new Rectangle(this.x, this.y, 60, 100);
        mass = 25;
        animator = new Animator();
        animator.AddAnimation("_CrouchWalk.png", 8, 0.6f, "walk");
        animator.AddAnimation("_Jump.png", 3, 0.3f, "jump");
        animator.StartAnimation("walk");
    }

    public void move()
    {
        float futureX = 0;
        float futureY = 0;

        if(Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) 
            futureX -= moveSpeed * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) 
            futureX += moveSpeed * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyJustPressed(Keys.DPAD_UP) && !isFalling)
            fallSpeed = 1000;

        futureY += fallSpeed*Gdx.graphics.getDeltaTime();
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
            fallSpeed -= mass;
        }

        batch.draw(animator.UpdateFrame(), this.x, this.y, 240, 360);
    }
}