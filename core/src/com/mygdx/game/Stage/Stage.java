package com.mygdx.game.Stage;
import com.mygdx.game.Entity.*;
import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Stage{

    protected Texture background;
    DelayedRemovalArray <Enemy> enemies;
    protected ArrayList<Floor> floors;
    protected MainCharacter mc;
    protected GameScreen game;
    public float stageHeight;
    float sw = Gdx.graphics.getWidth();
    float sh = Gdx.graphics.getHeight();
    public Stage(){
        floors = new ArrayList<Floor>();
        enemies = new DelayedRemovalArray<Enemy>();
    }

    public ArrayList<Floor> getFloors(){
        return floors;
    }

    public void addFloor(float x, float y, float w, float h){
        this.floors.add(new Floor(x, y, w, h));
    }
    public void addEnemy(float x, float y){
        this.enemies.add(new Enemy(x, y, mc));
    }

    protected void checkEndLevel()
    {

    }

    public void deadDelete(){
        for(Enemy enemy : this.enemies){
            if(!enemy.getAlive()){
                enemies.removeValue(enemy, true);
            }
        }
    }
    public void update(SpriteBatch batch){
        sw = Gdx.graphics.getWidth();
        sh = Gdx.graphics.getHeight();
        batch.draw(this.background, 0, stageHeight, sw, sh);
        for(Enemy enemy : this.enemies){
            enemy.update(batch);
        }
        for(Floor floor: this.floors){
            floor.update(batch);
        }

        checkEndLevel();
        deadDelete();
    }
}
