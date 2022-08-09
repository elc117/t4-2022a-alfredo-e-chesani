package com.mygdx.game.Stage;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Entity.King;
import com.mygdx.game.Entity.LevelBarrear;
import com.mygdx.game.Entity.MainCharacter;
import com.badlogic.gdx.Gdx;

public class Level2 extends Stage{
    King king;
    
    public Level2(GameScreen game)
    {
        this.gameScreen = game;
        this.zoom = game.getZoom();
        this.background = new Texture("background.png");
        this.mc = game.player;

        this.stageHeight = 0; //primeiro andar começa na altura zero
        zoom = game.getZoom();
 
        //addFloor(0, 0, 1*zoom, 0.05f); //chao
        addFloor(0, 0, 0.05f, 1*zoom); //parede da esquerda
        addFloor(zoom - 0.05f, 0, 0.05f, 1*zoom); //parede da direita
        //plataformas
        addFloor(0.8f, 0.4f, 0.3f, 0.08f);
        addFloor(1.2f, 0.7f, 0.3f, 0.08f);
        addFloor(1.4f, 1.1f, 0.3f, 0.08f);
        addFloor(1.6f, 1.4f, 0.3f, 0.08f);
        addFloor(1.8f, 1.8f, 0.3f, 0.08f);
        super.stageHeight = 2;
        super.barrear = new LevelBarrear(this);
    }

    @Override
    protected void checkEndLevel()
    {

        if(mc.getY() < height*stageHeight - offsetY)
        {
            gameScreen.updateStage(0);
            gameScreen.camera.position.y -= height;
            gameScreen.camera.update();
        }

        if(king != null && mc.attack().overlaps(king.hitBox))
        {
            gameScreen.game.setScreen(new TitleScreen(gameScreen.batch, gameScreen.game));
        }

    }
}
