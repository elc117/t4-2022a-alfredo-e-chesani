package com.mygdx.game.Stage;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Entity.King;
import com.mygdx.game.Entity.MainCharacter;
import com.badlogic.gdx.Gdx;

public class Level2 extends Stage{
    King king;
    
    public Level2(GameScreen game)
    {
        super(2);
        this.gameScreen = game;
        this.background = new Texture("background.png");
        this.mc = game.player;

        this.stageHeight = 0; //primeiro andar começa na altura zero
        zoom = game.getZoom();
        this.stageHeight = 2;
 
        //addFloor(0, 0, 1*zoom, 0.05f); //chao
        addFloor(0, 0, 0.05f, 1*zoom); //parede da esquerda
        addFloor(zoom - 0.05f, 0, 0.05f, 1*zoom); //parede da direita
        //plataformas
        addFloor(0.8f, 0.4f, 0.3f, 0.08f);
        addFloor(1.2f, 0.7f, 0.3f, 0.08f);
        addFloor(1.4f, 1.1f, 0.3f, 0.08f);
        addFloor(1.6f, 1.4f, 0.3f, 0.08f);
        addFloor(1.8f, 1.8f, 0.3f, 0.08f);
    }

    @Override
    protected void checkEndLevel()
    {
        float height = Gdx.graphics.getHeight();

        if(mc.getY() < height)
        {
            gameScreen.updateStage(0);
            gameScreen.camera.position.y -= height;
            gameScreen.camera.update();
        }

        if(king != null && king.hitBox.overlaps(mc.hitBox))
        {
            gameScreen.game.setScreen(new TitleScreen(gameScreen.batch, gameScreen.game));
        }

    }
}
