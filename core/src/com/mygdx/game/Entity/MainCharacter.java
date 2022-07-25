package com.mygdx.game.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Input.Keys;
import java.util.ArrayList;

public class MainCharacter extends Entity{

    public float moveSpeed = 600;
    public boolean gotHit = false;
    protected boolean isFalling = false;
    float futureX = 0;
    float futureY = 0;
    public MainCharacter()
    {
        this.sprite = new Texture("character_test.png");
        setXY(50, 200);
        this.hitBox = new Rectangle(this.x, this.y, 50, 100);
        mass = 40;
    }

    public void hit(){
        Rectangle collided = this.GetCollision();
        if(gotHit && !isFalling){
            fallSpeed = 1500;
            System.out.println(fallSpeed);
            new Thread(new Runnable(){ //usei thread pq se eu tento usar delay sem o programa congela
                @Override
                public void run(){
                    long time = System.currentTimeMillis();
                    while (System.currentTimeMillis() < time + 500){} //delay para nao conseguir se mover sem ter caido
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run(){
                            if(fallSpeed != 0)
                                gotHit = false;
                        }
                    });
                }
            }).start();
        }
    }
    public void move()
    {
        hit();
        futureX = 0;
        futureY = 0;
        if(Gdx.input.isKeyPressed(Keys.DPAD_LEFT) && !gotHit) 
            futureX -= moveSpeed * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Keys.DPAD_RIGHT) && !gotHit) 
            futureX += moveSpeed * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyJustPressed(Keys.DPAD_UP) && !isFalling)
            fallSpeed = 2000;

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

        if(fallSpeed > -2000)
        {
            fallSpeed -= 2*mass;
        }

        batch.draw(sprite, this.x, this.y, 50, 100);
    }
}