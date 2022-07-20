package com.mygdx.game.Stage;
import com.badlogic.gdx.graphics.Texture;
public class Level1 extends Stage{
    public Level1(){
        this.background = new Texture("background.png");
        addFloor(0, 0, 200, 50);
        addFloor(200, 200, 200, 50);
        addFloor(400, 400, 200, 50);
    }
}
