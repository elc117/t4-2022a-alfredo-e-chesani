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
        zoom = game.getZoom();
 
        addFloor(0, 0, 1*zoom, 0.02f); //chao
        addFloor(0.1f, 0, 0.02f, 1*zoom); //parede da esquerda
        addFloor(zoom - 0.1f - 0.02f, 0, 0.02f, 1*zoom); //parede da direita
        //plataformas
        addFloor(1.8f, 0.6f, 0.1f, 0.04f);
        addFloor(1.5f, 0.3f, 0.1f, 0.04f);
        addFloor(1.55f, 0.96f, 0.1f, 0.04f);
        addEnemy(1.8f, 0);
        super.stageHeight = 1;
        super.barrear = new LevelBarrear(this);
    }
    @Override
    protected void checkEndLevel()
    {

        if(mc.getY() > height*zoom - offsetY)
        {
            gameScreen.updateStage(1);
            gameScreen.camera.position.y += height;
            gameScreen.camera.update();
        }
    }
}
