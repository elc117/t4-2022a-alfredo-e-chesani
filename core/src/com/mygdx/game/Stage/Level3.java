package com.mygdx.game.Stage;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Entity.King;
import com.mygdx.game.Entity.LevelBarrear;
import com.mygdx.game.Entity.MainCharacter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class Level3 extends Stage{
    public Level3(Game game, GameScreen gameScreen){
        super(game);
        this.gameScreen = gameScreen;
        SetZoom(2);
        SetHeight(3);
        this.background = new Texture("background.png");
        this.mc = gameScreen.player;

        addFloor(0.1f, 0, 0.02f, 1*zoom); //parede da esquerda
        addFloor(zoom - 0.1f - 0.02f, 0, 0.02f, 1*zoom); //parede da direita

        //plataformas
        addFloor(0.7f, 0.1f, 0.1f, 0.05f);
        addFloor(1f, 0.3f, 0.1f, 0.05f);
        addFloor(0f+0.1f, 0.4f, 0.1f, 0.05f);
        addFloor(0.425f, 0.3f, 0.1f, 0.05f);
        addFloor(1.25f, 0.6f, 0.1f, 0.05f);
        addFloor(1.525f, 0.8f, 0.1f, 0.05f);
        addFloor(1.8f, 1f, 0.1f, 0.05f);
        addFloor(1.25f, 1.15f, 0.1f, 0.05f);
        addFloor(0f+0.1f, 1.4f, 0.1f, 0.05f);
        addFloor(0.9f, 1.15f, 0.1f, 0.05f);
        addFloor(0.45f, 1.15f, 0.1f, 0.05f);
        addFloor(0.4f, 1.7f, 0.1f, 0.05f);
        //inimigos
        addEnemy(1.7f, 1f);
        addEnemy(0f+0.1f+0.02f, 0.4f);
        addEnemy(0f+0.1f+0.02f, 1.4f);
        super.barrear = new LevelBarrear(this);
    }
    


    @Override
    protected void checkEndLevel()
    {
        if(mc.getY() > height*(stageHeight)*zoom - offsetY){
            if(!gameFinal){
                kingsRoom = Gdx.audio.newMusic(Gdx.files.internal("kingsRoom.mp3"));
                kingsRoom.setVolume(0.3f);
                kingsRoom.play();
            }
            gameFinal = true;
            gameScreen.castleTheme.dispose();
            gameScreen.updateStage(3);
            gameScreen.camera.position.y += height*zoom;
            gameScreen.camera.update();
        }
        if(mc.getY() < height*(stageHeight - 1)*zoom - offsetY)
        {
            gameScreen.updateStage(1);
            gameScreen.camera.position.y -= height*zoom;
            gameScreen.camera.update();
        }
    }
}