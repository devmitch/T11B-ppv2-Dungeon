package test;

import org.json.JSONArray;
import org.json.JSONObject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;


public class SwordDuelTest {

    public DungeonMockController setup1() {
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

    public DungeonMockController setup2() {
        try {
            JSONObject json = new JSONObject();
            json.put("width", 1);
            json.put("height", 4);
            JSONArray entities = new JSONArray();

            JSONObject player = new JSONObject();
            player.put("x", 0);
            player.put("y", 2);
            player.put("type", "player");
            entities.put(player);

            JSONObject sword = new JSONObject();
            sword.put("x", 0);
            sword.put("y", 0);
            sword.put("type", "sword");
            entities.put(sword);

            JSONObject potion = new JSONObject();
            potion.put("x", 0);
            potion.put("y", 1);
            potion.put("type", "invincibility");
            entities.put(potion);

            JSONObject enemy1 = new JSONObject();
            enemy1.put("x", 0);
            enemy1.put("y", 3);
            enemy1.put("type", "enemy");
            entities.put(enemy1);

            JSONObject switch1 = new JSONObject();
            switch1.put("x", 0);
            switch1.put("y", 3);
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
        DungeonMockController controller = setup1();
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

        // pick up sword
        controller.movePlayer(Direction.LEFT);
        // check sword entity is not on ground
        for (Entity e : dungeon.getEntitiesOnTile(0, 0)) {
            assertFalse(e instanceof Sword);
        }
        // check player has sword
        assertTrue(dungeon.getPlayer().getSword() != null);
        // check sword durability is 5
        assertEquals(dungeon.getPlayer().getSword().getDurability(), 5);

        // move right to duel enemy
        controller.movePlayer(Direction.RIGHT);
        // check the enemy dies and the player is alive
        for (Entity e : dungeon.getEntitiesOnTile(1, 0)) {
            assertTrue(e instanceof Player);
        }
        // check the sword durability is now 4
        assertEquals(dungeon.getPlayer().getSword().getDurability(), 4);

        // mow down the 4 remaining enemies by moving to x=9
        // enemies might not move uniformly if they get blocked, depends which ones 
        // are updated first, so we go to x=9 to guarantee fights with all of them
        controller.movePlayer(Direction.RIGHT);
        controller.movePlayer(Direction.RIGHT);
        controller.movePlayer(Direction.RIGHT);
        controller.movePlayer(Direction.RIGHT);
        controller.movePlayer(Direction.RIGHT);
        controller.movePlayer(Direction.RIGHT);
        controller.movePlayer(Direction.RIGHT);
        controller.movePlayer(Direction.RIGHT);
        // check the player is still alive
        for (Entity e : dungeon.getEntitiesOnTile(9, 0)) {
            assertTrue(e instanceof Player || e instanceof FloorSwitch);
        }
        // check all the enemies are deleted from dungeon
        for (int i = 0; i < dungeon.getWidth(); i++) {
            for (Entity e : dungeon.getEntitiesOnTile(i, 0)) {
                assertFalse(e instanceof Enemy);
            }
        }
        // check the sword urability went to 0
        assertTrue(dungeon.getPlayer().getSword().getDurability() == 0);
    }

    // Test for a player dying with multiple enemies around
    @Test
    public void SwordDuelTest2() {
        DungeonMockController controller = setup1();
        assertNotEquals(controller, null);
        Dungeon dungeon = controller.dungeon;
        // move player up so it dies
        controller.movePlayer(Direction.UP);
        // check player dies successfully
        for (int i = 0; i < dungeon.getWidth(); i++) {
            for (Entity e : dungeon.getEntitiesOnTile(i, 0)) {
                assertFalse(e instanceof Player);
            }
        }
        assertTrue(dungeon.getPlayer() == null);
    }

    // Checks that a player doesn't use their sword if they have invisibility potion
    @Test
    public void SwordDuelTest3() {
        DungeonMockController controller = setup2();
        assertNotEquals(controller, null);
        Dungeon dungeon = controller.dungeon;

        // move up twice to pick up sword and potion
        controller.movePlayer(Direction.UP);
        controller.movePlayer(Direction.UP);

        // move down to end to kill enemy
        controller.movePlayer(Direction.DOWN);
        controller.movePlayer(Direction.DOWN);
        controller.movePlayer(Direction.DOWN);
        controller.movePlayer(Direction.DOWN);
        // check enemy is dead
        for (int i = 0; i < dungeon.getHeight(); i++) {
            for (Entity e : dungeon.getEntitiesOnTile(0, i)) {
                assertFalse(e instanceof Enemy);
            }
        }
        
        // check sword still has 5 durability
        assertEquals(dungeon.getPlayer().getSword().getDurability(), 5);
    }
}