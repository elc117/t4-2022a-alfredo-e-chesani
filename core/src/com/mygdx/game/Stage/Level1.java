package com.mygdx.game.Stage;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Entity.MainCharacter;
import com.badlogic.gdx.Gdx;

public class Level1 extends Stage{
    public Level1(GameScreen game)
    {
        this.gameScreen = game;
        this.background = new Texture("background.png");
        this.mc = game.player;
        this.stageHeight = 0; //primeiro andar começa na altura zero

        addFloor(0, 0, Gdx.graphics.getWidth(), 50); //chao
        addFloor(0, 0, 50, Gdx.graphics.getHeight()); //parede da esquerda
        addFloor(Gdx.graphics.getWidth() - 50, 0, 50, Gdx.graphics.getHeight()); //parede da direita
        addFloor(200, 200, 200, 50);
        addFloor(400, 400, 200, 50);
        addFloor(600, 600, 200, 50);
        addFloor(150, 800, 200, 50);
        addEnemy(1000, 50);
    }

    @Override
    protected void checkEndLevel()
    {
        float height = Gdx.graphics.getHeight();

        if(mc.getY() > height)
        {
            gameScreen.updateStage(1);
            gameScreen.camera.position.y += height;
            gameScreen.camera.update();
        }
    }
}
