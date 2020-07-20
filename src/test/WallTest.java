package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Test;

import unsw.dungeon.*;

public class WallTest {
    
    public DungeonMockController setup() {
        
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

            JSONObject wall4 = new JSONObject();
            wall4.put("type", "wall");
            wall4.put("x", 2);
            wall4.put("y", 1);

            entities.put(player);
            entities.put(wall1);
            entities.put(wall2);
            entities.put(wall3);
            entities.put(wall4);

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


    @Test
    public void wallIsLoaded() {
        // checks if walls being loaded in crashes the game
        DungeonMockController controller = setup();
        
        assertNotEquals(null, controller);
    }

    @Test
    public void wallBlocksAllMovement() {
        // checks if walls block movement in any direction
        DungeonMockController controller = setup();

        Dungeon dungeon = controller.dungeon;

        assertEquals(1, dungeon.getPlayer().getY());
        assertEquals(1, dungeon.getPlayer().getX());

        // move in each direction

        controller.movePlayer(Direction.DOWN);
        assertEquals(1, dungeon.getPlayer().getY());
        assertEquals(1, dungeon.getPlayer().getX());

        controller.movePlayer(Direction.LEFT);
        assertEquals(1, dungeon.getPlayer().getY());
        assertEquals(1, dungeon.getPlayer().getX());

        controller.movePlayer(Direction.UP);
        assertEquals(1, dungeon.getPlayer().getY());
        assertEquals(1, dungeon.getPlayer().getX());

        controller.movePlayer(Direction.RIGHT);
        assertEquals(1, dungeon.getPlayer().getY());
        assertEquals(1, dungeon.getPlayer().getX());

    }

}