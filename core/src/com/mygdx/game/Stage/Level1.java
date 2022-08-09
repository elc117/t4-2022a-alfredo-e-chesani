package com.mygdx.game.Stage;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Entity.King;

public class Level1 extends Stage{
    float ew = width * 8/100; //largura do inimigo
    public Level1(GameScreen game)
    
    {
        this.gameScreen = game;
        this.background = new Texture("background.png");
       // king = new King(mc, new Rectangle(0,0,50,50));
        //king.setXY(-50, -50);
        this.mc = game.player;

        this.stageHeight = 0; //primeiro andar começa na altura zero
        zoom = game.getZoom();
 
        addFloor(0, 0, 1*zoom, 0.05f); //chao
        addFloor(0, 0, 0.05f, 1*zoom); //parede da esquerda
        addFloor(zoom - 0.05f, 0, 0.05f, 1*zoom); //parede da direita
        //plataformas
        addFloor(0.8f, 0.4f, 0.3f, 0.08f);
        addFloor(1.2f, 0.7f, 0.3f, 0.08f);

        //inimigo tá igual pq não entendi como esse tamnho dele funciona
        addEnemy(40, 40, 100);
    }

    @Override
    protected void checkEndLevel()
    {
        if(mc.getY() > height)
        {
            gameScreen.updateStage(1);
            gameScreen.camera.position.y += height;
            gameScreen.camera.update();
        }
    }
}
