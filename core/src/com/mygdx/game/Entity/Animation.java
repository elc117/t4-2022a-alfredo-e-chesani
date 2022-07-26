package com.mygdx.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.TextureArray;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation
{
    public TextureRegion frames[]; 
    float duration;
    float time;
    
    public Animation(int length, float duration)
    {
        frames = new TextureRegion[length];
        this.duration = duration;
        time = 0;
    }

    public TextureRegion GetFrame()
    {
        time = (Gdx.graphics.getDeltaTime() + time) % duration;
        int currentFrame = (int)((frames.length - 1)*(time/duration));

        return frames[currentFrame];
    }

    public void Reset()
    {
        time = 0;
    }

    
}
