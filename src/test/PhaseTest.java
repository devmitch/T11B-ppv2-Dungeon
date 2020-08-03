package test;

import org.json.JSONArray;
import org.json.JSONObject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;


public class PhaseTest {

    public DungeonMockController setup() {
        try {
            JSONObject json = new JSONObject();
            json.put("width", 7);
            json.put("height", 7);
            JSONArray entities = new JSONArray();

            JSONObject player = new JSONObject();
            player.put("x", 2);
            player.put("y", 0);
            player.put("type", "player");
            entities.put(player);

            JSONObject wall0 = new JSONObject();
            wall0.put("x", 3);
            wall0.put("y", 0);
            wall0.put("type", "wall");
            entities.put(wall0);

            JSONObject wall1 = new JSONObject();
            wall1.put("x", 3);
            wall1.put("y", 1);
            wall1.put("type", "wall");
            entities.put(wall1);

            JSONObject wall2 = new JSONObject();
            wall2.put("x", 3);
            wall2.put("y", 2);
            wall2.put("type", "wall");
            entities.put(wall2);

            JSONObject wall3 = new JSONObject();
            wall3.put("x", 3);
            wall3.put("y", 3);
            wall3.put("type", "wall");
            entities.put(wall3);

            JSONObject wall4 = new JSONObject();
            wall4.put("x", 3);
            wall4.put("y", 4);
            wall4.put("type", "wall");
            entities.put(wall4);
            
            JSONObject enemy = new JSONObject();
            enemy.put("x", 4);
            enemy.put("y", 0);
            enemy.put("type", "enemy");
            entities.put(enemy);

            JSONObject phasePotion = new JSONObject();
            phasePotion.put("x", 2);
            phasePotion.put("y", 1);
            phasePotion.put("type", "phase");
            entities.put(phasePotion);

            JSONObject switch1 = new JSONObject();
            switch1.put("x", 6);
            switch1.put("y", 6);
            switch1.put("type", "switch");
            entities.put(switch1);

            json.put("entities", entities);

            JSONObject goal = new JSONObject();
            goal.put("goal", "boulders");
            json.put("goal-condition", goal);

            DungeonMockControllerLoader dungeonLoader = new DungeonMockControllerLoader(json);

            DungeonMockController controller = dungeonLoader.loadController();
            return controller;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Test
    public void testPhaseLoaded() {
        DungeonMockController controller = setup();
        assertNotEquals(controller, null);
        Dungeon dungeon = controller.dungeon;

        // check that phase potion is loaded
        assertEquals(1, dungeon.getEntitiesOnTile(2, 1).size());
        assertTrue(dungeon.getEntitiesOnTile(2, 1).get(0) instanceof PhasePotion);
    }

    @Test
    public void testInvisible() {
        DungeonMockController controller = setup();
        assertNotEquals(controller, null);
        Dungeon dungeon = controller.dungeon;

        // move down to pick up potion
        controller.movePlayer(Direction.DOWN);

        // move down again and check that enemy hasnt moved
        controller.movePlayer(Direction.DOWN);
        assertTrue(dungeon.getEntitiesOnTile(4, 0).get(0) instanceof Enemy);

        //move down until potion runs out
        controller.movePlayer(Direction.DOWN);
        controller.movePlayer(Direction.DOWN);
        controller.movePlayer(Direction.DOWN);
        controller.movePlayer(Direction.DOWN);
        controller.movePlayer(Direction.DOWN);
        controller.movePlayer(Direction.DOWN);
        controller.movePlayer(Direction.DOWN);
        controller.movePlayer(Direction.DOWN);
        controller.movePlayer(Direction.DOWN);
        controller.movePlayer(Direction.DOWN);
        controller.movePlayer(Direction.DOWN);
        controller.movePlayer(Direction.DOWN);
        controller.movePlayer(Direction.DOWN);
        controller.movePlayer(Direction.DOWN);
        assertTrue(dungeon.getEntitiesOnTile(4, 0).get(0) instanceof Enemy);
        // not invisible anymore, next move, the enemy should move down
        controller.movePlayer(Direction.DOWN);
        assertTrue(dungeon.getEntitiesOnTile(4, 1).get(0) instanceof Enemy);
    }

    @Test
    public void testPhasing() {
        DungeonMockController controller = setup();
        assertNotEquals(controller, null);
        Dungeon dungeon = controller.dungeon;

        // move down to pick up potion
        controller.movePlayer(Direction.DOWN);
        controller.movePlayer(Direction.DOWN);

        // move right to hop over wall
        controller.movePlayer(Direction.RIGHT);
        // check that player is over wall
        assertTrue(dungeon.getEntitiesOnTile(4, 2).get(0) instanceof Player);
    }
}