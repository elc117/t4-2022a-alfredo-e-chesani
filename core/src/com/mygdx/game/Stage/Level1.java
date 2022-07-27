package com.mygdx.game.Stage;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Entity.MainCharacter;
import com.badlogic.gdx.Gdx;
public class Level1 extends Stage{
    public Level1(MainCharacter mc){
        this.background = new Texture("background.png");
        this.mc = mc;
        addFloor(0, 0, Gdx.graphics.getWidth(), 50); //chao
        addFloor(0, 0, 50, Gdx.graphics.getHeight()); //parede da esquerda
        addFloor(Gdx.graphics.getWidth() - 50, 0, 50, Gdx.graphics.getHeight()); //parede da direita
        addFloor(200, 200, 200, 50);
        addFloor(400, 400, 200, 50);
        addFloor(600, 600, 200, 50);
        addFloor(150, 800, 200, 50);
        //addEnemy(1000, 0);
    }
}
