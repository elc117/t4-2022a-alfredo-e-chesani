package com.mygdx.game.Stage;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Entity.King;

public class Level1 extends Stage{
    float ew = sw * 8/100; //largura do inimigo
    public Level1(GameScreen game)
    
    {
        this.gameScreen = game;
        this.background = new Texture("background.png");
        king.setXY(-50, -50);
        this.mc = game.player;
        this.stageHeight = 0; //primeiro andar começa na altura zero
        z = game.getZoom();
        float fd = sw / 50; //espessura dos floors
        addFloor(0-(sw/z), 0-(sh/z), sw*z, fd); //chao
        addFloor(0-(sw/z), 0-(sh/z), fd, sh*z); //parede da esquerda
        addFloor(sw+(sw/z)-fd, 0-(sh/z), fd, sh*z); //parede da direita
        //plataformas
        addFloor(sw*90/100 + sh/z, 0-(sh/z)*55/100, 200, fd);
        addFloor(sw*80/100, 0-(sh/z)*20/100, 200, fd);
        addEnemy((sw+sw/z)-ew*1.3f, 0-(sh/z)+(sh*4/100), ew);
    }

    @Override
    protected void checkEndLevel()
    {
        if(mc.getY() > sh)
        {
            gameScreen.updateStage(1);
            gameScreen.camera.position.y += sw;
            gameScreen.camera.update();
        }
    }
}
