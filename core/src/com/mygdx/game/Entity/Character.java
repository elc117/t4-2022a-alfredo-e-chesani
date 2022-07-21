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
    Vector2 direction = new Vector2(0,0);

    public Character()
    {
        this.sprite = new Texture("Colour1/NoOutline/120x80_PNGSheets/_CrouchTransition.png");
        setXY(50, 300);
        this.hitBox = new Rectangle(this.x, this.y, 50, 50);

        mass = 50;
    }

    public void move(Rectangle rects[])
    {
        float futureX = 0;
        float futureY = fallSpeed*Gdx.graphics.getDeltaTime();

        if(Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) 
            futureX -= moveSpeed * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) 
            futureX += moveSpeed * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyJustPressed(Keys.DPAD_UP) && !isFalling)
            fallSpeed = 1000;

        this.hitBox.x += futureX;
        Rectangle collided = GetCollision(rects);

        if(collided != null)
        {
            this.hitBox.x -= futureX;
        }

        this.hitBox.y += futureY;
        collided = GetCollision(rects);

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