package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Input.Keys;

public class Character extends Entity
{
    private boolean isFalling;
    private int moveSpeed = 400;

    public Character(){
        this.sprite = new Texture("Colour1/NoOutline/120x80_PNGSheets/_CrouchTransition.png");
        setXY(50, 300);
        this.hitBox = new Rectangle(this.x, this.y, 50, 50);

        mass = 50;
    }



    public void move(Rectangle rects[])
    {
        float futureX = 0;
        float futureY = 0;

        if(Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) 
            futureX -= moveSpeed * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) 
            futureX += moveSpeed * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyJustPressed(Keys.DPAD_UP) && !isFalling)
        {
            fallSpeed = 1000;
        }

        futureY += fallSpeed*Gdx.graphics.getDeltaTime();
        this.hitBox.x += futureX;
        Rectangle collided = this.GetCollision(rects);

        if(collided != null)
        {
            this.hitBox.x -= futureX;
        }

        this.hitBox.y += futureY;
        collided = this.GetCollision(rects);
        
        if(collided != null)
        {
            this.hitBox.y -= futureY;
            
            if(fallSpeed < 0)
            {
                hitBox.y = collided.y + collided.height;
                isFalling = false;
            }

            fallSpeed = 0;
        }
        else
        {
            isFalling = true;
        }

        this.y = hitBox.y;
        this.x = hitBox.x;
    }

    public void update(SpriteBatch batch, Rectangle rects[])
    {
        move(rects);

        if(fallSpeed > -2000)
        {
            fallSpeed -= mass;
        }

        batch.draw(sprite, this.x, this.y, 200, 200);
    }
}