package com.mygdx.game.Stage;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Entity.King;
import com.mygdx.game.Entity.LevelBarrear;
import com.mygdx.game.Entity.MainCharacter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class Final extends Stage{
    public Final(Game game, GameScreen gameScreen){
        super(game);
        super.gameScreen = gameScreen;
        this.mc = gameScreen.player;
        this.background = new Texture("kingsBackground.png");
        SetZoom(2);
        SetHeight(4);
        SetKing(1.8f, 0.02f); 
        addFloor(0.1f, 0, 0.02f, 1*zoom); //parede da esquerda
        addFloor(zoom - 0.1f - 0.02f, 0, 0.02f, 1*zoom); //parede da direita
        addFloor(0.6f, 0f, 1.3f, 0.02f); //chao
        super.barrear = new LevelBarrear(this);
    }




    @Override
    protected void checkEndLevel()
    {
        if(mc.getY() < height*(stageHeight - 1)*zoom - offsetY)
        {
            gameScreen.updateStage(2);
            gameScreen.camera.position.y -= height*zoom;
            gameScreen.camera.update();
        }
    }
}
