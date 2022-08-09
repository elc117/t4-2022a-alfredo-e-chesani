package com.mygdx.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class LevelBarrear extends Entity
{
    private Stage stage;
    private Animator animator;
    private int height;
    private int width;
    public int size = 25;

    public LevelBarrear(Stage stage)
    {
        this.stage = stage;
        animator = new Animator();
        animator.AddAnimation("barrearOpening.png", 5, 0.5f, "opening");
        animator.AddAnimation("barrearOpening.png", 1, 999.0f, "closed");
        animator.StartAnimation("closed");

        height = Gdx.graphics.getHeight();
        width = Gdx.graphics.getWidth();

        setXY(0, height - size);
        this.hitBox = new Rectangle(x,y,width,size);
    }


    public void update(SpriteBatch batch)
    {
    

        batch.draw(animator.UpdateFrame(), x, y);
    }

}
