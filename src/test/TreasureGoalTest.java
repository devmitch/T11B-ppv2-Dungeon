package test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import unsw.dungeon.*;

public class TreasureGoalTest {
    
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

            entities.put(player);

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

            JSONObject treasure = new JSONObject();
            treasure.put("type", "treasure");
            treasure.put("x", 2);
            treasure.put("y", 0);
            
            entities.put(player);
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

    public DungeonMockController setup3() {
        try {
            JSONObject json = new JSONObject();
            json.put("height", 1);
            json.put("width", 4);

            JSONArray entities = new JSONArray();

            JSONObject player = new JSONObject();
            player.put("x", 3);
            player.put("y", 0);
            player.put("type", "player");

            JSONObject treasure1 = new JSONObject();
            treasure1.put("type", "treasure");
            treasure1.put("x", 2);
            treasure1.put("y", 0);

            JSONObject treasure2 = new JSONObject();
            treasure2.put("type", "treasure");
            treasure2.put("x", 1);
            treasure2.put("y", 0);

            JSONObject treasure3 = new JSONObject();
            treasure3.put("type", "treasure");
            treasure3.put("x", 0);
            treasure3.put("y", 0);
            
            entities.put(player);
            entities.put(treasure1);
            entities.put(treasure2);
            entities.put(treasure3);

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

            JSONObject treasure1 = new JSONObject();
            treasure1.put("type", "treasure");
            treasure1.put("x", 2);
            treasure1.put("y", 0);

            JSONObject treasure2 = new JSONObject();
            treasure2.put("type", "treasure");
            treasure2.put("x", 1);
            treasure2.put("y", 0);

            entities.put(player);
            entities.put(treasure1);
            entities.put(treasure2);

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
    // treasure goal but no treasure
    // treasure goal with 1 treasure
    // treasure goal with 3 treasure
    // picking up treasure with no treasure goal

    @Test
    public void noTreasure() {
        DungeonMockController controller = setup1();

        assertNotEquals(null, controller);

        assertTrue(controller.dungeon.completedGoal());
    }

    @Test
    public void onePieceOfTreasure() {
        DungeonMockController controller = setup2();

        Dungeon dungeon = controller.dungeon;

        assertFalse(dungeon.completedGoal());

        dungeon.getPlayer().move(Direction.LEFT);

        assertTrue(dungeon.completedGoal());
    }

    @Test
    public void threePiecesOfTreasure() {
        DungeonMockController controller = setup3();

        Dungeon dungeon = controller.dungeon;

        assertFalse(dungeon.completedGoal());

        // pick up the first piece of treasure
        dungeon.getPlayer().move(Direction.LEFT);
        assertFalse(dungeon.completedGoal());

        // pick up the second piece of treasure
        dungeon.getPlayer().move(Direction.LEFT);
        assertFalse(dungeon.completedGoal());

        // pick up the third (and last) piece of treasure
        dungeon.getPlayer().move(Direction.LEFT);
        assertTrue(dungeon.completedGoal());
    }

    @Test
    public void noTreasureGoalAndPickingUpTreasure() {
        // picking up treasure is unrelated to the goal of this dungeon.
        DungeonMockController controller = setup4();

        Dungeon dungeon = controller.dungeon;

        assertTrue(dungeon.completedGoal());

        // pick up the first piece of treasure
        dungeon.getPlayer().move(Direction.LEFT);
        assertTrue(dungeon.completedGoal());

        // pick up the second piece of treasure
        dungeon.getPlayer().move(Direction.LEFT);
        assertTrue(dungeon.completedGoal());

        // pick up the third (and last) piece of treasure
        dungeon.getPlayer().move(Direction.LEFT);
        assertTrue(dungeon.completedGoal());
    }

}