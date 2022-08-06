package com.mygdx.game.Stage;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Entity.MainCharacter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.*;
public class Level1 extends Stage{
    public Level1(GameScreen game)
    {
        this.game = game;
        this.background = new Texture("background.png");
        this.mc = game.player;
        this.stageHeight = 0; //primeiro andar começa na altura zero
        float z = game.getZoom();

        addFloor(0-(sw/z), 0-(sh/z), sw*z, 50); //chao
        addFloor(0-(sw/z), 0-(sh/z), 50, sh*z); //parede da esquerda
        addFloor(sw+(sw/z)-50, 0-(sh/z), 50, sh*z); //parede da direita
        addFloor(200, 200, 200, 50);
        addFloor(400, 400, 200, 50);
        addFloor(600, 600, 200, 50);
        addFloor(150, 800, 200, 50);
        addEnemy(1000, 50);
    }

    @Override
    protected void checkEndLevel()
    {
        int height = Gdx.graphics.getHeight();

        if(mc.getY() > height)
        {
            game.updateStage(1);
            game.camera.position.y += height;
            game.camera.update();
        }
    }
}
