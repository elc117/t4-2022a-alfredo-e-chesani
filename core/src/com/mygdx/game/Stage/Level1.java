package com.mygdx.game.Stage;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Entity.King;

public class Level1 extends Stage{
    public Level1(GameScreen game)
    
    {
        this.gameScreen = game;
        this.background = new Texture("background.png");
        king = new King(mc, new Rectangle(0,0,50,50));
        king.setXY(-50, -50);
        this.mc = game.player;
        this.stageHeight = 0; //primeiro andar começa na altura zero
        z = game.getZoom();
        float fd = 50; //espessura dos floors
        addFloor(0-(sw/z), 0-(sh/z), sw*z, fd); //chao
        addFloor(0-(sw/z), 0-(sh/z), fd, sh*z); //parede da esquerda
        addFloor(sw+(sw/z)-fd, 0-(sh/z), fd, sh*z); //parede da direita
        //plataformas
        addFloor(2300, 0-(sh/z) + 200, 200, fd);
        addFloor(2650, 0-(sh/z) + 500, 200, fd);
        addFloor(2450, 0-(sh/z) + 850, 200, fd);
        addFloor(1850, 0-(sh/z) + 650, 200, fd);
        addFloor(1300, 0-(sh/z) + 950, 200, fd);
        addFloor(850, 0-(sh/z) + 1150, 200, fd);
        addFloor(500, 0-(sh/z) + 1450, 200, fd);
        addFloor(100, 0-(sh/z) + 850, 200, fd);
        addFloor(-400, 0-(sh/z) + 650, 200, fd);
        addFloor(-300, 0-(sh/z) + 1200, 200, fd);
        addFloor(-450, 0-(sh/z) + 1500, 200, fd);
        addFloor(0, 0-(sh/z) + 1800, 200, fd);
        addFloor(0-(sw/z) + 50, 0-(sh/z) + 850, 200, fd);
        addFloor(2650, 0-(sh/z) + 1200, 200, fd);
        addFloor(0-(sw/z) + 50, 0-(sh/z) + 1500, 200, fd);
        addEnemy(0-(sw/z) + 50, 0-(sh/z) + 1500+50);
        addEnemy(0-(sw/z) + 50, 0-(sh/z) + 850+50);
        addEnemy(2715, 0-(sh/z)+50);
        addEnemy(2700, 0-(sh/z) + 1200 + 50);
    }

    @Override
    protected void checkEndLevel()
    {
        if(mc.getY() > sh+(sh/z))
        {
            gameScreen.updateStage(1);
            gameScreen.camera.position.y += sw;
            gameScreen.camera.update();
        }
    }
}
