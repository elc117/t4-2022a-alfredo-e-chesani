package com.mygdx.game.Entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;

public class King extends Entity
{
    Animator animator;
    MainCharacter player;
    Action onEndGame;

    public King(MainCharacter player, Rectangle rect)
    {
        animator = new Animator();
        animator.AddAnimation("kingStand.png", 5, 1.0f, "kingStand");
        animator.StartAnimation("kingStand");
        hitBox = rect;
        setXY(rect.x, rect.y);
        this.player = player;
    }

    public void update(SpriteBatch batch)
    {
        batch.draw(animator.UpdateFrame(), x, y);
    }


}
