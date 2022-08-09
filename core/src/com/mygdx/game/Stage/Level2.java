package com.mygdx.game.Stage;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Entity.King;
import com.mygdx.game.Entity.MainCharacter;
import com.badlogic.gdx.Gdx;

public class Level2 extends Stage{
    King king;
    
    public Level2(GameScreen game)
    {
        this.gameScreen = game;
        float z = game.getZoom();
        this.background = new Texture("background.png");
        this.mc = game.player;
        float h = Gdx.graphics.getHeight();
        this.stageHeight =  h + h/z;//segundo andar
        
      //  addFloor(0, 0, Gdx.graphics.getWidth(), 50); //chao
         //addFloor(0, 0, 50, Gdx.graphics.getHeight()); //parede da esquerda
        //addFloor(Gdx.graphics.getWidth() - 50, 0, 50, Gdx.graphics.getHeight()); //parede da direita
        addFloor(200, 200 + stageHeight, 200, 50);
        addFloor(400, 400 + stageHeight, 200, 50);
        addFloor(600, 600 + stageHeight, 200, 50);
        addFloor(150, 800 + stageHeight, 200, 50);
        addFloor(175, 0 + stageHeight, 200, 50);
    }

    @Override
    protected void checkEndLevel()
    {
        float h = Gdx.graphics.getHeight();
        float height = h + h/2;

        if(mc.getY() < height)
        {
            gameScreen.updateStage(0);
            gameScreen.camera.position.y -= sw;
            gameScreen.camera.update();
        }

        if(king != null && mc.attack().overlaps(king.hitBox))
        {
            gameScreen.game.setScreen(new TitleScreen(gameScreen.batch, gameScreen.game));
        }

    }
}
