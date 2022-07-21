package com.mygdx.game.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;

public class Floor extends Entity{
    float w;
    float h;
    public Floor(float X, float Y, float w, float h){
        this.h = h;
        this.w = w;
        setXY(X, Y);
        this.hitBox = new Rectangle(this.x, this.y, w, h);
        this.sprite = new Texture("floor.jpg");
    }

    Rectangle GetCollision(ArrayList<Floor> rects)
    {
        return null;
    }

    public void update(SpriteBatch batch, ArrayList<Floor> rects){
        batch.draw(sprite, this.x, this.y, w, h);
    }
}
