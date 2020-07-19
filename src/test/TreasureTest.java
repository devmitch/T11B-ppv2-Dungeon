package test;

import org.json.JSONArray;
import org.json.JSONObject;

import unsw.dungeon.*;

public class TreasureTest {
    
    public DungeonMockController setup() {
        
        JSONObject json = new JSONObject();

        DungeonMockControllerLoader dungeonLoader = new DungeonMockControllerLoader(json);

        DungeonMockController controller = dungeonLoader.loadController();
        return controller;
    }

}