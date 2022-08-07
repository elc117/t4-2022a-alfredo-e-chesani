package com.mygdx.game.Entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;

public class King extends Entity
{
    Animator animator;
    MainCharacter player;
    Action onEndGame;

    public King(MainCharacter player)
    {
        animator = new Animator();
        animator.AddAnimation("stand.png", 3, 0.5f, "kingStand");
        animator.StartAnimation("kingStand");
        this.player = player;
    }

    public void update(SpriteBatch batch)
    {
        batch.draw(animator.UpdateFrame(), x, y);
    }


}
