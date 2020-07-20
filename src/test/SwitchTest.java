package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import unsw.dungeon.*;

public class SwitchTest {
 
    public DungeonMockController setup1() {

        try {
            JSONObject json = new JSONObject();
            json.put("height", 1);
            json.put("width", 4);

            JSONArray entities = new JSONArray();

            JSONObject player = new JSONObject();
            player.put("x", 3);
            player.put("y", 0);
            player.put("type", "player");

            JSONObject boulder1 = new JSONObject();
            boulder1.put("type", "boulder");
            boulder1.put("x", 2);
            boulder1.put("y", 0);

            JSONObject floorSwitch = new JSONObject();
            floorSwitch.put("type", "switch");
            floorSwitch.put("x", 1);
            floorSwitch.put("y", 0);

            entities.put(player);
            entities.put(boulder1);
            entities.put(floorSwitch);

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

    public DungeonMockController setup2() {

        try {
            JSONObject json = new JSONObject();
            json.put("height", 1);
            json.put("width", 4);

            JSONArray entities = new JSONArray();

            JSONObject player = new JSONObject();
            player.put("x", 3);
            player.put("y", 0);
            player.put("type", "player");

            JSONObject boulder1 = new JSONObject();
            boulder1.put("type", "boulder");
            boulder1.put("x", 2);
            boulder1.put("y", 0);

            JSONObject treasure = new JSONObject();
            treasure.put("type", "treasure");
            treasure.put("x", 1);
            treasure.put("y", 0);

            JSONObject floorSwitch = new JSONObject();
            floorSwitch.put("type", "switch");
            floorSwitch.put("x", 1);
            floorSwitch.put("y", 0);

            entities.put(player);
            entities.put(boulder1);
            entities.put(treasure);
            entities.put(floorSwitch);

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

    // cases:
    // switch is an empty tile (can move over it)
    // can push a boulder onto the tile
    // checking if the switch is activated is done in the switch goal test
    
    @Test
    public void switchCanBeLoaded() {
        DungeonMockController controller = setup1();

        assertNotEquals(null, controller);

        Dungeon dungeon = controller.dungeon;

        assertEquals(1, dungeon.getEntitiesOnTile(1, 0).size());
        assertTrue(dungeon.getEntitiesOnTile(1, 0).get(0) instanceof FloorSwitch);
    }

    @Test
    public void switchIsNotAnObstruction() {
        DungeonMockController controller = setup1();

        Dungeon dungeon = controller.dungeon;

        // push boulder onto switch
        dungeon.getPlayer().move(Direction.LEFT);

        assertEquals(2, dungeon.getEntitiesOnTile(1, 0).size());
        assertTrue(dungeon.getEntitiesOnTile(1, 0).get(0) instanceof FloorSwitch);
        assertTrue(dungeon.getEntitiesOnTile(1, 0).get(1) instanceof Boulder);
        assertTrue(dungeon.completedGoal()); // basic switch goal test

        // move player onto switch
        dungeon.getPlayer().move(Direction.LEFT);

        assertEquals(2, dungeon.getEntitiesOnTile(1, 0).size());
        assertTrue(dungeon.getEntitiesOnTile(1, 0).get(0) instanceof FloorSwitch);
        assertTrue(dungeon.getEntitiesOnTile(1, 0).get(1) instanceof Player);

        // move player off of switch
        dungeon.getPlayer().move(Direction.RIGHT);

        assertEquals(1, dungeon.getEntitiesOnTile(1, 0).size());
        assertTrue(dungeon.getEntitiesOnTile(1, 0).get(0) instanceof FloorSwitch);
    }

    // item placed underneath a switch (this is mainly for test coverage)
    
    @Test
    public void treasureUnderneathSwitch() {
        DungeonMockController controller = setup2();

        Dungeon dungeon = controller.dungeon;

        // push boulder onto switch
        dungeon.getPlayer().move(Direction.LEFT);

        // there should be 3 items
        assertEquals(3, dungeon.getEntitiesOnTile(1, 0).size());
        assertTrue(dungeon.getEntitiesOnTile(1, 0).get(0) instanceof Treasure);
        assertTrue(dungeon.getEntitiesOnTile(1, 0).get(1) instanceof FloorSwitch);
        assertTrue(dungeon.getEntitiesOnTile(1, 0).get(2) instanceof Boulder);
        assertTrue(dungeon.completedGoal()); // basic switch goal test

        // move player onto switch
        dungeon.getPlayer().move(Direction.LEFT);

        assertEquals(2, dungeon.getEntitiesOnTile(1, 0).size());
        assertTrue(dungeon.getEntitiesOnTile(1, 0).get(0) instanceof FloorSwitch);
        assertTrue(dungeon.getEntitiesOnTile(1, 0).get(1) instanceof Player);

        // move player off of switch
        dungeon.getPlayer().move(Direction.RIGHT);

        assertEquals(1, dungeon.getEntitiesOnTile(1, 0).size());
        assertTrue(dungeon.getEntitiesOnTile(1, 0).get(0) instanceof FloorSwitch);
    }
}