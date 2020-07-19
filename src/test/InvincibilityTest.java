package test;

import org.json.JSONArray;
import org.json.JSONObject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;


public class InvincibilityTest {

    public DungeonMockController setup() {
        try {
            JSONObject json = new JSONObject();
            json.put("width", 20);
            json.put("height", 3);
            JSONArray entities = new JSONArray();

            JSONObject player = new JSONObject();
            player.put("x", 0);
            player.put("y", 0);
            player.put("type", "player");
            entities.put(player);

            JSONObject switch1 = new JSONObject();
            switch1.put("x", 19);
            switch1.put("y", 0);
            switch1.put("type", "switch");
            entities.put(switch1);

            for (int i = 1; i < 20; i++) {
                if (i == 9) continue;
                JSONObject wall = new JSONObject();
                wall.put("x", i);
                wall.put("y", 1);
                wall.put("type", "wall");
                entities.put(wall);
            }

            for (int i = 1; i < 20; i++) {
                JSONObject wall = new JSONObject();
                wall.put("x", i);
                wall.put("y", 2);
                wall.put("type", "wall");
                entities.put(wall);
            }

            JSONObject enemy = new JSONObject();
            enemy.put("x", 8);
            enemy.put("y", 0);
            enemy.put("type", "enemy");
            entities.put(enemy);

            JSONObject potion = new JSONObject();
            potion.put("x", 1);
            potion.put("y", 0);
            potion.put("type", "invincibility");
            entities.put(potion);

            JSONObject door = new JSONObject();
            door.put("x", 9);
            door.put("y", 0);
            door.put("type", "door");
            door.put("id", 0);
            entities.put(door);

            JSONObject key = new JSONObject();
            key.put("x", 0);
            key.put("y", 1);
            key.put("type", "key");
            key.put("id", 0);
            entities.put(key);

            JSONObject portal1 = new JSONObject();
            portal1.put("x", 0);
            portal1.put("y", 2);
            portal1.put("type", "portal");
            portal1.put("id", 0);
            entities.put(portal1);

            JSONObject portal2 = new JSONObject();
            portal2.put("x", 9);
            portal2.put("y", 1);
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

    // Tests that enemy runs away from player when invincible
    // Tests that enemy stops running when out of range
    // Tests 
    @Test
    public void InvincibilityTest1() {
        DungeonMockController controller = setup();
        assertNotEquals(controller, null);
        Dungeon dungeon = controller.dungeon;
    }
}
