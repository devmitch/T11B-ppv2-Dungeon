package test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import unsw.dungeon.*;

public class EnemyGoalTest {
    
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
            goal.put("goal", "enemies");
            
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
            json.put("width", 3);

            JSONArray entities = new JSONArray();

            JSONObject player = new JSONObject();
            player.put("x", 0);
            player.put("y", 0);
            player.put("type", "player");
            entities.put(player);

            JSONObject enemy = new JSONObject();
            enemy.put("x", 2);
            enemy.put("y", 0);
            enemy.put("type", "enemy");
            entities.put(enemy);

            JSONObject sword = new JSONObject();
            sword.put("x", 1);
            sword.put("y", 0);
            sword.put("type", "sword");
            entities.put(sword);

            json.put("entities", entities);

            JSONObject goal = new JSONObject();
            goal.put("goal", "enemies");
            
            json.put("goal-condition", goal);

            DungeonMockControllerLoader dungeonLoader = new DungeonMockControllerLoader(json);
    
            DungeonMockController controller = dungeonLoader.loadController();
            return controller;
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
            player.put("x", 0);
            player.put("y", 0);
            player.put("type", "player");
            entities.put(player);

            JSONObject enemy1 = new JSONObject();
            enemy1.put("x", 2);
            enemy1.put("y", 0);
            enemy1.put("type", "enemy");
            entities.put(enemy1);

            JSONObject enemy2 = new JSONObject();
            enemy2.put("x", 3);
            enemy2.put("y", 0);
            enemy2.put("type", "enemy");
            entities.put(enemy2);

            JSONObject sword = new JSONObject();
            sword.put("x", 1);
            sword.put("y", 0);
            sword.put("type", "sword");
            entities.put(sword);

            json.put("entities", entities);

            JSONObject goal = new JSONObject();
            goal.put("goal", "enemies");
            
            json.put("goal-condition", goal);

            DungeonMockControllerLoader dungeonLoader = new DungeonMockControllerLoader(json);
    
            DungeonMockController controller = dungeonLoader.loadController();
            return controller;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Test
    public void noEnemies() {
        DungeonMockController controller = setup1();
        assertNotEquals(null, controller);
        Dungeon dungeon = controller.dungeon;
        assertTrue(dungeon.completedGoal());
    }

    @Test
    public void oneEnemyWin() {
        DungeonMockController controller = setup2();
        assertNotEquals(null, controller);
        Dungeon dungeon = controller.dungeon;

        // check dungeon isn't completed
        assertFalse(dungeon.completedGoal());
        // move right, pick up sword, and kill enemy
        controller.movePlayer(Direction.RIGHT);
        // check enemy is dead
        for (int i = 0; i < dungeon.getWidth(); i++) {
            for (Entity entity : dungeon.getEntitiesOnTile(i, 0)) {
                assertFalse(entity instanceof Enemy);
            }
        }
        // check dungeon is completed
        assertTrue(dungeon.completedGoal());
    }

    @Test
    public void oneEnemyLoss() {
        DungeonMockController controller = setup2();
        assertNotEquals(null, controller);
        Dungeon dungeon = controller.dungeon;
        // check dungeon isn't completed
        assertFalse(dungeon.completedGoal());
        // move left until enemy kills player
        controller.movePlayer(Direction.LEFT);
        controller.movePlayer(Direction.LEFT);
        // check player is dead
        assertTrue(dungeon.getPlayer() == null);
        // check enemy is alive
        for (Entity entity : dungeon.getEntitiesOnTile(0, 0)) {
            assertTrue(entity instanceof Enemy);
        }
        // check dungeon goal isn't complete
        assertFalse(dungeon.completedGoal());
    }

    @Test
    public void multipleEnemies() {
        DungeonMockController controller = setup3();
        assertNotEquals(null, controller);
        Dungeon dungeon = controller.dungeon;
        // check dungeon isn't completed
        assertFalse(dungeon.completedGoal());
        // move right, pick up sword, kill enemy
        controller.movePlayer(Direction.RIGHT);
        // move to end of dungeon to kill other enemy
        controller.movePlayer(Direction.RIGHT);
        controller.movePlayer(Direction.RIGHT);
        // check all enemies are dead
        for (int i = 0; i < dungeon.getWidth(); i++) {
            for (Entity entity : dungeon.getEntitiesOnTile(i, 0)) {
                assertFalse(entity instanceof Enemy);
            }
        }
        // check dungeon is completed
        assertTrue(dungeon.completedGoal());
    }

}