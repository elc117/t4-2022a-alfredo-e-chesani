package com.mygdx.game.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
public class Projectile extends Entity{
    Vector2 direcao;
    public Projectile(float x, float y, Vector2 direcao){
        this.direcao = direcao;
        setXY(x, y);
        this.hitBox = new Rectangle(this.x, this.y, 50, 50);
    }

    public void update(SpriteBatch batch, TextureRegion frame){
        batch.draw(frame, this.x, this.y, this.hitBox.width, this.hitBox.height);
        this.x += direcao.nor().x * 700 * Gdx.graphics.getDeltaTime();
        this.y += direcao.nor().y * 700 * Gdx.graphics.getDeltaTime();
        this.hitBox.x = this.x;
        this.hitBox.y = this.y;
    }
}
