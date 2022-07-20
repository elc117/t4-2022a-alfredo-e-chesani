package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Input.Keys;

public class Character extends Entity{
    puclic Character(){
        this.sprite = new Texture("Colour1/NoOutline/120x80_PNGSheets/_CrouchTransition.png");
        setXY(0, 0);
    }
}