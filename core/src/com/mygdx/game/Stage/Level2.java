package com.mygdx.game.Stage;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Entity.MainCharacter;
import com.badlogic.gdx.Gdx;

public class Level2 extends Stage{
    public Level2(GameScreen game)
    {
        this.game = game;
        this.background = new Texture("background.png");
        this.mc = game.player;
        this.stageHeight = Gdx.graphics.getHeight();//segundo andar

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
        float height = Gdx.graphics.getHeight();

        if(mc.getY() < height)
        {
            game.updateStage(0);
            game.camera.position.y -= height;
            game.camera.update();
        }
    }
}
