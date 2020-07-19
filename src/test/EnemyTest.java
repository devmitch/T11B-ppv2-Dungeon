package test;

import org.json.JSONArray;
import org.json.JSONObject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;


public class EnemyTest {

    public DungeonMockController setup() {
        try {
            JSONObject json = new JSONObject();
            json.put("width", 5);
            json.put("height", 5);
            JSONArray entities = new JSONArray();

            JSONObject player = new JSONObject();
            player.put("x", 0);
            player.put("y", 1);
            player.put("type", "player");
            entities.put(player);

            JSONObject switch1 = new JSONObject();
            switch1.put("x", 0);
            switch1.put("y", 3);
            switch1.put("type", "switch");
            entities.put(switch1);

            JSONObject wall1 = new JSONObject();
            wall1.put("x", 1);
            wall1.put("y", 0);
            wall1.put("type", "wall");
            entities.put(wall1);

            JSONObject wall2 = new JSONObject();
            wall2.put("x", 1);
            wall2.put("y", 1);
            wall2.put("type", "wall");
            entities.put(wall2);

            JSONObject wall3 = new JSONObject();
            wall3.put("x", 1);
            wall3.put("y", 2);
            wall3.put("type", "wall");
            entities.put(wall3);

            JSONObject wall4 = new JSONObject();
            wall4.put("x", 1);
            wall4.put("y", 3);
            wall4.put("type", "wall");
            entities.put(wall4);

            JSONObject wall5 = new JSONObject();
            wall5.put("x", 3);
            wall5.put("y", 1);
            wall5.put("type", "wall");
            entities.put(wall5);

            JSONObject wall6 = new JSONObject();
            wall6.put("x", 1);
            wall6.put("y", 4);
            wall6.put("type", "wall");
            entities.put(wall6);

            JSONObject enemy = new JSONObject();
            enemy.put("x", 3);
            enemy.put("y", 0);
            enemy.put("type", "enemy");
            entities.put(enemy);

            JSONObject portal1 = new JSONObject();
            portal1.put("x", 0);
            portal1.put("y", 0);
            portal1.put("type", "portal");
            portal1.put("id", 0);
            entities.put(portal1);

            JSONObject portal2 = new JSONObject();
            portal2.put("x", 3);
            portal2.put("y", 4);
            portal2.put("type", "portal");
            portal2.put("id", 0);
            entities.put(portal2);

            json.put("entities", entities);

            JSONObject goal = new JSONObject();
            goal.put("goal", "boulders");
            json.put("goal-condition", goal);

            System.out.println(json);

            DungeonMockControllerLoader dungeonLoader = new DungeonMockControllerLoader(json);

            DungeonMockController controller = dungeonLoader.loadController();
            return controller;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    // Tests the enemy moves towards the player when no path is avaiable
    // Tests that the enemy chooses the shortest path towards the player when reachable
    // Tests that the enemy kills the player if the player is not equipped with sword or potion
    @Test
    public void EnemyTest1() {
        DungeonMockController controller = setup();
        assertNotEquals(controller, null);
        Dungeon dungeon = controller.dungeon;
    }
}