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
    protected DelayedRemovalArray <Enemy> enemies;
    protected LevelBarrear barrear;
    protected ArrayList<Floor> floors;
    protected MainCharacter mc;
    protected GameScreen gameScreen;
    protected King king;
    public float stageHeight;
    protected int height;
    protected int width;
    public float offsetX;
    public float offsetY;
    public float zoom;
    
    float fd; //espessura dos floors
    public Stage(){
        floors = new ArrayList<Floor>();
        enemies = new DelayedRemovalArray<Enemy>();
        barrear = new LevelBarrear(this);
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        offsetX = -width*(zoom-1)/2;
        offsetY = -height*(zoom-1)/2;

        king = null;
    }

    public ArrayList<Floor> getFloors(){
        return floors;
    }

    public DelayedRemovalArray<Enemy> GetEnemyList()
    {
        return this.enemies;
    }

    public void addFloor(float x, float y, float w, float h)
    {
        x *= width;
        y *= height;
        w *= width;
        h *= height;
        
        this.floors.add(new Floor(x - offsetX, y - offsetY, w, h));
    }
    public void addEnemy(float x, float y, float h)
    {
        this.enemies.add(new Enemy(x - offsetX, y - offsetY, mc, h));
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
        batch.draw(this.background, 0 - offsetX, stageHeight - offsetY, width*zoom, height*zoom);
        for(Enemy enemy : this.enemies){
            enemy.update(batch);
        }
        for(Floor floor: this.floors){
            floor.update(batch);
        }

        if(king != null)
        {
            king.update(batch);
        }

        barrear.update(batch);

        checkEndLevel();
        deadDelete();
    }
}
