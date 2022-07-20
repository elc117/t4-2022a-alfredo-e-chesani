package com.mygdx.game.Stage;
import com.mygdx.game.Entity.*;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Stage{

    Texture background;

    protected ArrayList<Floor> floors;

    public Stage(){
        floors = new ArrayList<Floor>();
    }


    public ArrayList<Floor> getFloors(){
        return floors;
    }

    public void addFloor(float x, float y, float w, float h){
        this.floors.add(new Floor(x, y, w, h));
    }

    public void update(SpriteBatch batch){
        batch.draw(this.background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
}
