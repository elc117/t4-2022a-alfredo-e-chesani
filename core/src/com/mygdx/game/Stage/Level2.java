package com.mygdx.game.Stage;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Entity.King;
import com.mygdx.game.Entity.LevelBarrear;
import com.mygdx.game.Entity.MainCharacter;
import com.badlogic.gdx.Gdx;

public class Level2 extends Stage{
    King king;
    
    public Level2(GameScreen game)
    {
        this.gameScreen = game;
        SetZoom(2);
        SetHeight(2);
        this.background = new Texture("background.png");
        this.mc = game.player;
 
        //addFloor(0, 0, 1*zoom, 0.05f); //chao
        addFloor(0.1f, 0, 0.05f, 1*zoom); //parede da esquerda
        addFloor(zoom - 0.05f - 0.1f, 0, 0.05f, 1*zoom); //parede da direita
        //plataformas
        addFloor(1.4f, 0.3f, 0.1f, 0.05f);
        addFloor(1.1f, 0.15f, 0.1f, 0.05f);
        king = new King(mc, new Rectangle(offsetX, stageOffset + offsetY, 50,50));

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

        if(king != null && mc.hitBox.overlaps(king.hitBox))
        {
            gameScreen.game.setScreen(new EndScreen(gameScreen.batch, gameScreen.game));
        }

    }
}
