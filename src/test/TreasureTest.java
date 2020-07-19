package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import unsw.dungeon.*;

public class TreasureTest {
    
    public DungeonMockController setup1() {

        try {
            JSONObject json = new JSONObject();
            json.put("height", 3);
            json.put("width", 3);

            JSONArray entities = new JSONArray();

            JSONObject player = new JSONObject();
            player.put("x", 1);
            player.put("y", 1);
            player.put("type", "player");

            JSONObject wall1 = new JSONObject();
            wall1.put("type", "wall");
            wall1.put("x", 0);
            wall1.put("y", 1);

            JSONObject wall2 = new JSONObject();
            wall2.put("type", "wall");
            wall2.put("x", 1);
            wall2.put("y", 0);

            JSONObject wall3 = new JSONObject();
            wall3.put("type", "wall");
            wall3.put("x", 1);
            wall3.put("y", 2);

            JSONObject treasure = new JSONObject();
            treasure.put("type", "treasure");
            treasure.put("x", 2);
            treasure.put("y", 1);

            entities.put(player);
            entities.put(wall1);
            entities.put(wall2);
            entities.put(wall3);
            entities.put(treasure);

            json.put("entities", entities);

            JSONObject goal = new JSONObject();
            goal.put("goal", "boulders");
            
            json.put("goal-condition", goal);

            DungeonMockControllerLoader dungeonLoader = new DungeonMockControllerLoader(json);

            DungeonMockController controller = dungeonLoader.loadController();
            return controller;
        } catch (Exception e) {
            return null;
        }
    }

    // treasure is picked up by the player
    // treasure is removed from the dungeon

    @Test
    public void treasureIsLoaded() {
        // checks if loading treasure crashes the game
        DungeonMockController controller = setup1();

        assertNotEquals(null, controller);
    }

    @Test
    public void treasureIsInteractable() {
        // checks if treasure is removed from the game when the player picks it up
        DungeonMockController controller = setup1();

        Dungeon dungeon = controller.dungeon;

        // check treasure exists on tile
        assertEquals(1, dungeon.getEntitiesOnTile(2, 1).size());

        dungeon.getPlayer().move(Direction.RIGHT);
        dungeon.getPlayer().move(Direction.LEFT);

        // check that treasure no longer exists on tile
        assertEquals(0, dungeon.getEntitiesOnTile(2, 1).size());
    }

}