package com.mygdx.game.Stage;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Entity.King;
import com.mygdx.game.Entity.LevelBarrear;
import com.mygdx.game.Entity.MainCharacter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class Level2 extends Stage{
    King king;
    
    public Level2(Game game, GameScreen gameScreen)
    {
        super(game);
        this.gameScreen = gameScreen;
        SetZoom(2);
        SetHeight(2);
        this.background = new Texture("background.png");
        this.mc = gameScreen.player;
        
        //addFloor(0, 0, 1*zoom, 0.05f); //chao
        addFloor(0.1f, 0, 0.05f, 1*zoom); //parede da esquerda
        addFloor(zoom - 0.05f - 0.1f, 0, 0.05f, 1*zoom); //parede da direita
        //plataformas
        addFloor(1.4f, 0.3f, 0.1f, 0.05f);
        addFloor(1.07f, 0.04f, 0.1f, 0.05f);
        SetKing(1.15f,0.15f);

        super.barrear = new LevelBarrear(this);
    }

    @Override
    protected void checkEndLevel()
    {
        if(mc.getY() < height*(stageHeight - 1)*zoom - offsetY)
        {
            gameScreen.updateStage(0);
            gameScreen.camera.position.y -= height*zoom;
            gameScreen.camera.update();
        }

    }
}
