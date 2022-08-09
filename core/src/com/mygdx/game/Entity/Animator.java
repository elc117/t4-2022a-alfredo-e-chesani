package com.mygdx.game.Entity;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animator 
{
    Map<String, Animation> animations;
    protected String curretAnimation;

    public Animator()
    {
        animations = new HashMap<String, Animation>();
    }

    public void StartAnimation(String name)
    {
        curretAnimation = name;
    }

    public void AddAnimation(String fileName, int cols, int rows,int offset, int lenght, float duration, String animationName)
    {
        Texture newSheet = new Texture(Gdx.files.internal(fileName));
        int width = newSheet.getWidth();
        int height = newSheet.getHeight();

        TextureRegion[][] tmp = TextureRegion.split(newSheet, width/cols, height/rows);
        animations.put(animationName, new Animation(lenght, duration));

       for(int i = 0; i < lenght; i++)
       {
            int l = (offset + i) / cols;
            int c = (i + offset) % cols;
   
            animations.get(animationName).frames[i] = tmp[l][c];
       }
    }
    public void AddAnimation(String fileName, int cols, float duration, String animationName)
    {
        AddAnimation(fileName, cols, 1, 0, cols, duration, animationName);
    }

    public boolean CheckLoop()
    {
        return animations.get(curretAnimation).hasLooped;
    }

    public TextureRegion UpdateFrame()
    {
        return animations.get(curretAnimation).GetFrame();
    }

}
