package com.mygdx.game.Stage;
import com.mygdx.game.Entity.*;
import java.util.ArrayList;

import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Stage{

    protected Texture background;
    protected DelayedRemovalArray <Enemy> enemies;
    protected LevelBarrear barrear;
    protected ArrayList<Floor> floors;
    protected MainCharacter mc;
    protected GameScreen gameScreen;
    protected Game game;
    protected King king;
    protected int height;
    protected int width;
    public float offsetX;
    public float offsetY;
    public float stageHeight;
    public float stageOffset;
    protected float zoom;
    
    float fd; //espessura dos floors
    public Stage(Game game){
        this.game = game;
        floors = new ArrayList<Floor>();
        enemies = new DelayedRemovalArray<Enemy>();
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        king = null;
    }

    public void SetKing(float x, float y)
    {
        x *= width;
        y *= height;
        king = new King(mc, new Rectangle(x - offsetX, y - offsetY + stageOffset, 50, 50));
    }

    public void SetZoom(float zoom)
    {
        this.zoom = zoom;
        offsetX = width*(zoom-1)/2;
        offsetY = height*(zoom-1)/2;
    }

    public void SetHeight(int level)
    {
        stageHeight = level;
        stageOffset = (level - 1)*height*zoom;
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
        
        this.floors.add(new Floor(x - offsetX, y - offsetY + stageOffset, w, h));
    }

    public void addEnemy(float x, float y)
    {
        x *= width;
        y *= height;
        this.enemies.add(new Enemy(x - offsetX, y + 15 - offsetY + stageOffset, mc));
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

        
        if(Gdx.input.isKeyPressed(Keys.K))
        {
            for (Enemy enemy : enemies) 
            {
                enemy.alive = false;
            }
        }

        batch.draw(this.background, 0 - offsetX, stageOffset - offsetY, width*zoom, height*zoom);
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

        if(king != null && mc.hitBox.overlaps(king.hitBox))
        {
            gameScreen.EndGame();
        }
    }
}
