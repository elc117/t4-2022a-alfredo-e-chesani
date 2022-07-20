package com.mygdx.game.Stage;
import com.mygdx.game.Entity.*;
import java.util.ArrayList;

public class Stage{

    protected ArrayList<Floor> floors;

    public Stage(){
        floors = new ArrayList<Floor>();

    }


    public ArrayList<Floor> getFloors(){
        return floors;
    }

    public void addFloor(float x, float y, float w, float h){
        this.floors.add(new Floor(x, y, w, h));
    }
}
