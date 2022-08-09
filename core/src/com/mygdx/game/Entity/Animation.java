package com.mygdx.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.TextureArray;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation
{
    public TextureRegion frames[]; 
    float duration;
    float time;
    public boolean hasLooped = false;

    public Animation(int length, float duration)
    {
        frames = new TextureRegion[length];
        this.duration = duration;
        time = 0;
    }

    public TextureRegion GetFrame()
    {
        time += Gdx.graphics.getDeltaTime();
        
        if((int)(time/duration) == 1)
        {
            hasLooped = true;
        }
        
        int currentFrame = (int)((frames.length - 1)*(time/duration));
        
        if(time >= duration)
        {
            time = 0;
            currentFrame = frames.length - 1;
        }
        
        return frames[currentFrame];
    }

    public void Reset()
    {
        time = 0;
    }

}
