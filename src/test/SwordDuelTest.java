package test;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import unsw.dungeon.*;


public class SwordDuelTest {

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

            JSONObject sword = new JSONObject();
            sword.put("x", 0);
            sword.put("y", 0);
            sword.put("type", "sword");
            entities.put(sword);

            JSONObject enemy1 = new JSONObject();
            enemy1.put("x", 2);
            enemy1.put("y", 0);
            enemy1.put("type", "enemy");
            entities.put(enemy1);

            JSONObject enemy2 = new JSONObject();
            enemy2.put("x", 6);
            enemy2.put("y", 0);
            enemy2.put("type", "enemy");
            entities.put(enemy2);

            JSONObject enemy3 = new JSONObject();
            enemy3.put("x", 7);
            enemy3.put("y", 0);
            enemy3.put("type", "enemy");
            entities.put(enemy3);

            JSONObject enemy4 = new JSONObject();
            enemy4.put("x", 8);
            enemy4.put("y", 0);
            enemy4.put("type", "enemy");
            entities.put(enemy4);

            JSONObject enemy5 = new JSONObject();
            enemy5.put("x", 9);
            enemy5.put("y", 0);
            enemy5.put("type", "enemy");
            entities.put(enemy5);

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

    @Test
    public void SwordDuelTest1() {
        DungeonMockController controller = setup();
        assertNotEquals(controller, null);
        Dungeon dungeon = controller.dungeon;

        // check all the entities were spawned in correctly
        assertTrue(dungeon.getEntitiesOnTile(0, 0).get(0) instanceof Sword);
        assertTrue(dungeon.getEntitiesOnTile(1, 0).get(0) instanceof Player);
        assertTrue(dungeon.getEntitiesOnTile(2, 0).get(0) instanceof Enemy);
        assertTrue(dungeon.getEntitiesOnTile(6, 0).get(0) instanceof Enemy);
        assertTrue(dungeon.getEntitiesOnTile(7, 0).get(0) instanceof Enemy);
        assertTrue(dungeon.getEntitiesOnTile(8, 0).get(0) instanceof Enemy);
        assertTrue(dungeon.getEntitiesOnTile(9, 0).get(0) instanceof Enemy);

        // check player doesn't have sword
        assertTrue(dungeon.getPlayer().getSword() == null);
    }
}