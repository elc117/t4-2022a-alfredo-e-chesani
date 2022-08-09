package com.mygdx.game.Stage;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Entity.King;
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
 
        addFloor(0, 0, 1*zoom, 0.05f); //chao
        addFloor(0, 0, 0.05f, 1*zoom); //parede da esquerda
        addFloor(zoom - 0.05f, 0, 0.05f, 1*zoom); //parede da direita
        //plataformas
        addFloor(0.8f, 0.4f, 0.3f, 0.08f);
        addFloor(1.2f, 0.7f, 0.3f, 0.08f);
        addFloor(1.4f, 1.1f, 0.3f, 0.08f);
        addFloor(1.6f, 1.4f, 0.3f, 0.08f);
        addFloor(1.8f, 1.8f, 0.3f, 0.08f);

        addEnemy(20, 20);
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
