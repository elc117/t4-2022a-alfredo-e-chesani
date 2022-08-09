package com.mygdx.game.Entity;

import java.time.chrono.IsoChronology;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.DelayedRemovalArray;

public class LevelBarrear extends Entity
{
    private com.mygdx.game.Stage.Stage stage;
    private Animator animator;
    private boolean hasLooped = false;
    private boolean isOpen = false;
    private int height;
    private int width;
    public int size = 25;

    public LevelBarrear(com.mygdx.game.Stage.Stage stage)
    {
        this.stage = stage;
        animator = new Animator();
        animator.AddAnimation("barrearOpening.png", 5, 1.0f, "opening");
        animator.AddAnimation("barrearOpening.png", 5, 1, 0, 1, 999.0f,"closed");
        animator.AddAnimation("barrearOpening.png", 5, 1, 4, 1, 999.0f, "open");
        animator.StartAnimation("closed");

        height = Gdx.graphics.getHeight();
        width = Gdx.graphics.getWidth();

        setXY(0, height - size);
        this.hitBox = new Rectangle(x,y,width,size);
    }


    public void update(SpriteBatch batch)
    {
       
        DelayedRemovalArray list = stage.GetEnemyList();
        
        if(list.size == 0 && !isOpen)
        {
            animator.StartAnimation("opening");
            this.hitBox.width = 0;
            isOpen = true;
            hasLooped = false;
        }
        
        hasLooped = animator.CheckLoop();

        if(hasLooped && isOpen)
        {
            animator.StartAnimation("open");
        }
        batch.draw(animator.UpdateFrame(), x, y, width, size);
    }

}
