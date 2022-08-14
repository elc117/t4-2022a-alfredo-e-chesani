package com.mygdx.game.Stage;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Entity.King;
import com.mygdx.game.Entity.LevelBarrear;
import com.mygdx.game.Entity.MainCharacter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class Level2 extends Stage{
    public Level2(Game game, GameScreen gameScreen)
    {
        super(game);
        this.gameScreen = gameScreen;
        SetZoom(2);
        SetHeight(2);
        this.background = new Texture("background.png");
        this.mc = gameScreen.player;
        //paredes
        addFloor(0.1f, 0, 0.02f, 1*zoom); //parede da esquerda
        addFloor(zoom - 0.1f - 0.02f, 0, 0.02f, 1*zoom); //parede da direita
        //plataformas
        addFloor(1.4f, 0.3f, 0.1f, 0.05f);
        addFloor(1.07f, 0.04f, 0.1f, 0.05f);
        addFloor(1.7f, 0.6f, 0.1f, 0.05f);
        addFloor(1.5f, 0.9f, 0.1f, 0.05f);
        addFloor(1.1f, 0.9f, 0.1f, 0.05f);
        addFloor(0.7f, 0.9f, 0.1f, 0.05f);
        addFloor(0.4f, 1.1f, 0.1f, 0.05f);
        addFloor(0f+0.1f, 0.7f, 0.1f, 0.05f);
        addFloor(1.8f, 1.6f, 0.1f, 0.05f);
        addFloor(1.65f, 1.25f, 0.1f, 0.05f);
        addFloor(0f+0.1f, 1.3f, 0.1f, 0.05f);
        addFloor(0.3f, 1.6f, 0.1f, 0.05f);
        addFloor(0.5f, 1.8f, 0.1f, 0.05f);
        //inimigois
        addEnemy(1.7f, 0.6f);
        addEnemy(0f+0.1f+0.02f, 0.7f);
        addEnemy(0f+0.1f+0.02f, 1.3f);

        //SetKing(1.15f,0.15f);

        super.barrear = new LevelBarrear(this);
    }

    @Override
    protected void checkEndLevel()
    {
        
        if(mc.getY() > height*(stageHeight)*zoom - offsetY){
            gameScreen.updateStage(2);
            gameScreen.camera.position.y += height*zoom;
            gameScreen.camera.update();
        }
        if(mc.getY() < height*(stageHeight - 1)*zoom - offsetY)
        {
            gameScreen.updateStage(0);
            gameScreen.camera.position.y -= height*zoom;
            gameScreen.camera.update();
        }

    }
}
