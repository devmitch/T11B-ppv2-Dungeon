package test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import unsw.dungeon.*;

public class SwitchGoalTest {
    
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

            JSONObject boulder = new JSONObject();
            boulder.put("type", "boulder");
            boulder.put("x", 2);
            boulder.put("y", 0);

            entities.put(player);
            entities.put(boulder);

            json.put("entities", entities);

            JSONObject goal = new JSONObject();
            goal.put("goal", "boulders");
            
            json.put("goal-condition", goal);

            DungeonMockControllerLoader dungeonLoader = new DungeonMockControllerLoader(json);
    
            DungeonMockController controller = dungeonLoader.loadController();
            return controller;
        } catch (Exception e) {
            
            System.out.println(e.getMessage());
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

            JSONObject boulder = new JSONObject();
            boulder.put("type", "boulder");
            boulder.put("x", 2);
            boulder.put("y", 0);

            JSONObject floorSwitch = new JSONObject();
            floorSwitch.put("type", "switch");
            floorSwitch.put("x", 1);
            floorSwitch.put("y", 0);

            entities.put(player);
            entities.put(boulder);
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

    public DungeonMockController setup3() {
        try {
            JSONObject json = new JSONObject();
            json.put("height", 1);
            json.put("width", 5);

            JSONArray entities = new JSONArray();

            JSONObject player = new JSONObject();
            player.put("x", 4);
            player.put("y", 0);
            player.put("type", "player");

            JSONObject boulder = new JSONObject();
            boulder.put("type", "boulder");
            boulder.put("x", 3);
            boulder.put("y", 0);

            JSONObject floorSwitch1 = new JSONObject();
            floorSwitch1.put("type", "switch");
            floorSwitch1.put("x", 2);
            floorSwitch1.put("y", 0);

            JSONObject floorSwitch2 = new JSONObject();
            floorSwitch2.put("type", "switch");
            floorSwitch2.put("x", 1);
            floorSwitch2.put("y", 0);

            entities.put(player);
            entities.put(boulder);
            entities.put(floorSwitch1);
            entities.put(floorSwitch2);

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

    public DungeonMockController setup4() {
        try {
            JSONObject json = new JSONObject();
            json.put("height", 1);
            json.put("width", 4);

            JSONArray entities = new JSONArray();

            JSONObject player = new JSONObject();
            player.put("x", 3);
            player.put("y", 0);
            player.put("type", "player");

            JSONObject floorSwitch1 = new JSONObject();
            floorSwitch1.put("type", "switch");
            floorSwitch1.put("x", 2);
            floorSwitch1.put("y", 0);

            JSONObject boulder = new JSONObject();
            boulder.put("type", "boulder");
            boulder.put("x", 2);
            boulder.put("y", 0);

            entities.put(player);
            entities.put(floorSwitch1);
            entities.put(boulder);
            

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

    public DungeonMockController setup5() {
        try {
            JSONObject json = new JSONObject();
            json.put("height", 1);
            json.put("width", 5);

            JSONArray entities = new JSONArray();

            JSONObject player = new JSONObject();
            player.put("x", 4);
            player.put("y", 0);
            player.put("type", "player");

            JSONObject boulder = new JSONObject();
            boulder.put("type", "boulder");
            boulder.put("x", 3);
            boulder.put("y", 0);

            JSONObject floorSwitch1 = new JSONObject();
            floorSwitch1.put("type", "switch");
            floorSwitch1.put("x", 2);
            floorSwitch1.put("y", 0);

            JSONObject floorSwitch2 = new JSONObject();
            floorSwitch2.put("type", "switch");
            floorSwitch2.put("x", 1);
            floorSwitch2.put("y", 0);

            JSONObject treasure = new JSONObject();
            treasure.put("type", "treasure");
            treasure.put("x", 0);
            treasure.put("y", 0);

            entities.put(player);
            entities.put(boulder);
            entities.put(floorSwitch1);
            entities.put(floorSwitch2);
            entities.put(treasure);

            json.put("entities", entities);

            JSONObject goal = new JSONObject();
            goal.put("goal", "treasure");
            
            json.put("goal-condition", goal);

            DungeonMockControllerLoader dungeonLoader = new DungeonMockControllerLoader(json);
    
            DungeonMockController controller = dungeonLoader.loadController();
            return controller;
        } catch (Exception e) {
            return null;
        }
    }

    @Test
    public void noSwitches() {
        DungeonMockController controller = setup1();

        assertNotEquals(null, controller);

        Dungeon dungeon = controller.dungeon;
        
        assertTrue(dungeon.completedGoal());
    }

    @Test
    public void pushingABoulderOnAndOffASwitch() {
        DungeonMockController controller = setup2();

        Dungeon dungeon = controller.dungeon;
        
        assertFalse(dungeon.completedGoal());

        // push the boulder onto the only switch
        dungeon.getPlayer().move(Direction.LEFT);
        assertTrue(dungeon.completedGoal());
    }

    @Test
    public void pushingABoulderOntoAnotherSwitch() {
        DungeonMockController controller = setup3();

        Dungeon dungeon = controller.dungeon;

        // push the boulder onto the first switch
        dungeon.getPlayer().move(Direction.LEFT);
        assertFalse(dungeon.completedGoal());

        // push the boulder onto the second switch
        dungeon.getPlayer().move(Direction.LEFT);
        assertFalse(dungeon.completedGoal());

        // there aren't enough boulders to complete this goal
    }

    @Test
    public void boulderSpawningOnASwitch() {
        DungeonMockController controller = setup4();

        Dungeon dungeon = controller.dungeon;

        // the goal should be considered completed from the start
        assertTrue(dungeon.completedGoal());
        
        // NOTE: It is up to the dungeon designer to place the switch before the boulder in the
        // .json file (so that the boulder can check if it is occupies the same tile as a switch).

        // player pushes boulder off switch
        dungeon.getPlayer().move(Direction.LEFT);
        assertFalse(dungeon.completedGoal());
    }

    @Test
    public void noBoulderGoalAndPushingBoulders() {
        DungeonMockController controller = setup5();

        Dungeon dungeon = controller.dungeon;

        // the goal isn't related to boulders, so it should always be false
        assertFalse(dungeon.completedGoal());

        dungeon.getPlayer().move(Direction.LEFT);
        assertFalse(dungeon.completedGoal());

        dungeon.getPlayer().move(Direction.LEFT);
        assertFalse(dungeon.completedGoal());
    }

}