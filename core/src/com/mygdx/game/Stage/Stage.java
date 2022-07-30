package com.mygdx.game.Stage;
import com.mygdx.game.Entity.*;
import java.util.ArrayList;
<<<<<<< HEAD

import com.badlogic.gdx.Game;
=======
import com.badlogic.gdx.utils.DelayedRemovalArray;
>>>>>>> 1d6bfef41b22d539e234fcc4cc54148ac3b360da
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
<<<<<<< HEAD

    protected void checkEndLevel()
    {

    }

=======
    public void deadDelete(){
        for(Enemy enemy : this.enemies){
            if(!enemy.getAlive()){
                enemies.removeValue(enemy, true);
            }
        }
    }
>>>>>>> 1d6bfef41b22d539e234fcc4cc54148ac3b360da
    public void update(SpriteBatch batch){
        batch.draw(this.background, 0, stageHeight, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        for(Enemy enemy : this.enemies){
            enemy.update(batch);
        }
        for(Floor floor: this.floors){
            floor.update(batch);
        }
<<<<<<< HEAD

        checkEndLevel();
=======
        deadDelete();
>>>>>>> 1d6bfef41b22d539e234fcc4cc54148ac3b360da
    }
}

