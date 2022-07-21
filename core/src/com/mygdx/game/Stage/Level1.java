package com.mygdx.game.Stage;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Entity.MainCharacter;
public class Level1 extends Stage{
    public Level1(MainCharacter mc){
        this.background = new Texture("background.png");
        this.mc = mc;
        addFloor(0, 0, 200, 50);
        addFloor(200, 200, 200, 50);
        addFloor(400, 400, 200, 50);
        addEnemy(300, 200);
        addEnemy(500, 400);
    }
}
