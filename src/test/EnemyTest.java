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
            json.put("width", 10);
            json.put("height", 1);
            JSONArray entities = new JSONArray();

            JSONObject player = new JSONObject();
            player.put("x", 1);
            player.put("y", 0);
            player.put("type", "player");
            entities.put(player);

            JSONObject switch1 = new JSONObject();
            switch1.put("x", 9);
            switch1.put("y", 0);
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

    /* Tests the enemy moves towards the player when no path is avaiable and that
    the enemy is able to kill the player when it doesn't have a sword or potion */
    @Test
    public void EnemyTest1() {
        DungeonMockController controller = setup();
        assertNotEquals(controller, null);
        Dungeon dungeon = controller.dungeon;
    }
}