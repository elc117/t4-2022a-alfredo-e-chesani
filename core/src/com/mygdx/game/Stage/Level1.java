package com.mygdx.game.Stage;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Entity.LevelBarrear;

public class Level1 extends Stage{

    float ew = width * 8/100; //largura do inimigo

    public Level1(GameScreen game)
    {
        super.gameScreen = game;
        this.background = new Texture("background.png");
       // king = new King(mc, new Rectangle(0,0,50,50));
        //king.setXY(-50, -50);
        this.mc = game.player;
        SetZoom(2);
        SetHeight(1);
 
        addFloor(0, 0, 1*zoom, 0.02f); //chao
        addFloor(0.1f, 0, 0.02f, 1*zoom); //parede da esquerda
        addFloor(zoom - 0.1f - 0.02f, 0, 0.02f, 1*zoom); //parede da direita
        //plataformas
        addFloor(1.8f, 0.6f, 0.1f, 0.05f);
        addFloor(1.5f, 0.3f, 0.1f, 0.05f);
        addFloor(1.55f, 0.959f, 0.1f, 0.05f);
        addFloor(1.8f, 1.3f, 0.1f, 0.05f);
        addFloor(1.2f, 0.75f, 0.1f, 0.05f);
        addFloor(0.825f, 1f, 0.1f, 0.05f);
        addFloor(0.45f, 1.2f, 0.1f, 0.05f);
        addFloor(0.7f, 1.45f, 0.1f, 0.05f);
        addFloor(0.9f, 1.7f, 0.1f, 0.05f);
        addFloor(0f+0.1f, 0.6f, 0.1f, 0.05f);
        addFloor(0f+0.1f, 1.4f, 0.1f, 0.05f);
        
        //inimigos
        //addEnemy(1.8f, 0);
        //addEnemy(1.8f, 1.3f);
        //addEnemy(0f+0.1f+0.02f, 0.6f);
        //addEnemy(0f+0.1f+0.02f, 1.4f);
        super.stageHeight = 1;
        super.barrear = new LevelBarrear(this);
    }
    @Override
    protected void checkEndLevel()
    {

        if(mc.getY() > height*zoom - offsetY)
        {
            gameScreen.updateStage(1);
            gameScreen.camera.position.y += height*zoom;
            gameScreen.camera.update();
        }
    }
}
