package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import unsw.dungeon.*;

public class BoulderTest {
    
    public DungeonMockController setup1() {

        try {
            JSONObject json = new JSONObject();
            json.put("height", 5);
            json.put("width", 5);

            JSONArray entities = new JSONArray();

            JSONObject player = new JSONObject();
            player.put("x", 2);
            player.put("y", 2);
            player.put("type", "player");

            JSONObject boulder1 = new JSONObject();
            boulder1.put("type", "boulder");
            boulder1.put("x", 1);
            boulder1.put("y", 2);

            JSONObject boulder2 = new JSONObject();
            boulder2.put("type", "boulder");
            boulder2.put("x", 2);
            boulder2.put("y", 1);

            JSONObject boulder3 = new JSONObject();
            boulder3.put("type", "boulder");
            boulder3.put("x", 2);
            boulder3.put("y", 3);

            JSONObject boulder4 = new JSONObject();
            boulder4.put("type", "boulder");
            boulder4.put("x", 3);
            boulder4.put("y", 2);

            entities.put(player);
            entities.put(boulder1);
            entities.put(boulder2);
            entities.put(boulder3);
            entities.put(boulder4);

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
            json.put("height", 5);
            json.put("width", 5);

            JSONArray entities = new JSONArray();

            JSONObject player = new JSONObject();
            player.put("x", 2);
            player.put("y", 2);
            player.put("type", "player");

            JSONObject boulder1 = new JSONObject();
            boulder1.put("type", "boulder");
            boulder1.put("x", 1);
            boulder1.put("y", 2);

            JSONObject boulder2 = new JSONObject();
            boulder2.put("type", "boulder");
            boulder2.put("x", 2);
            boulder2.put("y", 1);

            JSONObject boulder3 = new JSONObject();
            boulder3.put("type", "boulder");
            boulder3.put("x", 2);
            boulder3.put("y", 3);

            JSONObject boulder4 = new JSONObject();
            boulder4.put("type", "boulder");
            boulder4.put("x", 3);
            boulder4.put("y", 2);

            JSONObject blockBoulder = new JSONObject();
            blockBoulder.put("type", "boulder");
            blockBoulder.put("x", 2);
            blockBoulder.put("y", 0);

            JSONObject door = new JSONObject();
            door.put("type", "door");
            door.put("id", 7);
            door.put("x", 0);
            door.put("y", 2);

            JSONObject portal = new JSONObject();
            portal.put("type", "portal");
            portal.put("id", 1);
            portal.put("x", 4);
            portal.put("y", 2);

            JSONObject wall = new JSONObject();
            wall.put("type", "wall");
            wall.put("x", 2);
            wall.put("y", 4);

            entities.put(player);
            entities.put(boulder1);
            entities.put(boulder2);
            entities.put(boulder3);
            entities.put(boulder4);
            entities.put(blockBoulder);
            entities.put(door);
            entities.put(portal);
            entities.put(wall);

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
            json.put("width", 4);

            JSONArray entities = new JSONArray();

            JSONObject player = new JSONObject();
            player.put("x", 3);
            player.put("y", 0);
            player.put("type", "player");

            JSONObject boulder1 = new JSONObject();
            boulder1.put("type", "boulder");
            boulder1.put("x", 1);
            boulder1.put("y", 0);

            entities.put(player);
            entities.put(boulder1);

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
            player.put("x", 2);
            player.put("y", 0);
            player.put("type", "player");

            JSONObject boulder1 = new JSONObject();
            boulder1.put("type", "boulder");
            boulder1.put("x", 1);
            boulder1.put("y", 0);

            JSONObject enemy = new JSONObject();
            enemy.put("type", "enemy");
            enemy.put("x", 0);
            enemy.put("y", 0);

            entities.put(player);
            entities.put(boulder1);
            entities.put(enemy);

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

    @Test
    public void bouldersAreLoaded() {
        DungeonMockController controller = setup1();

        assertNotEquals(null, controller);

        Dungeon dungeon = controller.dungeon;

        // check boulders are on expected spot
        assertEquals(1, dungeon.getEntitiesOnTile(1, 2).size());
        assertEquals(1, dungeon.getEntitiesOnTile(2, 1).size());
        assertEquals(1, dungeon.getEntitiesOnTile(2, 3).size());
        assertEquals(1, dungeon.getEntitiesOnTile(3, 2).size());

        // check they are boulders
        assertTrue(dungeon.getEntitiesOnTile(1, 2).get(0) instanceof Boulder);
        assertTrue(dungeon.getEntitiesOnTile(2, 1).get(0) instanceof Boulder);
        assertTrue(dungeon.getEntitiesOnTile(2, 3).get(0) instanceof Boulder);
        assertTrue(dungeon.getEntitiesOnTile(3, 2).get(0) instanceof Boulder);
    }

    @Test
    public void bouldersCanMove() {
        DungeonMockController controller = setup1();

        Dungeon dungeon = controller.dungeon;

        // push the each boulder
        dungeon.getPlayer().move(Direction.LEFT);
        dungeon.getPlayer().move(Direction.RIGHT);

        dungeon.getPlayer().move(Direction.RIGHT);
        dungeon.getPlayer().move(Direction.LEFT);
        
        dungeon.getPlayer().move(Direction.UP);
        dungeon.getPlayer().move(Direction.DOWN);

        dungeon.getPlayer().move(Direction.DOWN);
        dungeon.getPlayer().move(Direction.UP);

        // check they are in the expected positions
        assertEquals(1, dungeon.getEntitiesOnTile(0, 2).size());
        assertEquals(1, dungeon.getEntitiesOnTile(2, 0).size());
        assertEquals(1, dungeon.getEntitiesOnTile(4, 2).size());
        assertEquals(1, dungeon.getEntitiesOnTile(2, 4).size());

        // check they are boulders
        assertTrue(dungeon.getEntitiesOnTile(0, 2).get(0) instanceof Boulder);
        assertTrue(dungeon.getEntitiesOnTile(2, 0).get(0) instanceof Boulder);
        assertTrue(dungeon.getEntitiesOnTile(4, 2).get(0) instanceof Boulder);
        assertTrue(dungeon.getEntitiesOnTile(2, 4).get(0) instanceof Boulder);
    }

    @Test
    public void cannotMoveBoulderThroughObstruction() {
        DungeonMockController controller = setup2();

        Dungeon dungeon = controller.dungeon;

        // push the each boulder
        dungeon.getPlayer().move(Direction.LEFT);
        dungeon.getPlayer().move(Direction.RIGHT);

        dungeon.getPlayer().move(Direction.RIGHT);
        dungeon.getPlayer().move(Direction.LEFT);
        
        dungeon.getPlayer().move(Direction.UP);
        dungeon.getPlayer().move(Direction.DOWN);

        dungeon.getPlayer().move(Direction.DOWN);
        dungeon.getPlayer().move(Direction.UP);

        // check they are in the expected positions
        assertEquals(1, dungeon.getEntitiesOnTile(1, 2).size());
        assertEquals(1, dungeon.getEntitiesOnTile(2, 1).size());
        assertEquals(1, dungeon.getEntitiesOnTile(2, 3).size());
        assertEquals(1, dungeon.getEntitiesOnTile(3, 2).size());

        // check they are boulders
        assertTrue(dungeon.getEntitiesOnTile(1, 2).get(0) instanceof Boulder);
        assertTrue(dungeon.getEntitiesOnTile(2, 1).get(0) instanceof Boulder);
        assertTrue(dungeon.getEntitiesOnTile(2, 3).get(0) instanceof Boulder);
        assertTrue(dungeon.getEntitiesOnTile(3, 2).get(0) instanceof Boulder);
    }

    @Test
    public void playerMustMoveIntoTheBoulder() {
        DungeonMockController controller = setup3();

        Dungeon dungeon = controller.dungeon;

        assertEquals(1, dungeon.getEntitiesOnTile(1, 0).size());
        assertTrue(dungeon.getEntitiesOnTile(1, 0).get(0) instanceof Boulder);

        // move the player
        dungeon.getPlayer().move(Direction.LEFT);

        assertEquals(1, dungeon.getEntitiesOnTile(1, 0).size());
        assertTrue(dungeon.getEntitiesOnTile(1, 0).get(0) instanceof Boulder);

        // move the player again
        dungeon.getPlayer().move(Direction.LEFT);

        assertEquals(1, dungeon.getEntitiesOnTile(0, 0).size());
        assertTrue(dungeon.getEntitiesOnTile(0, 0).get(0) instanceof Boulder);

        // check that the boulder cannot move out of the map
        dungeon.getPlayer().move(Direction.LEFT);

        assertEquals(1, dungeon.getEntitiesOnTile(0, 0).size());
        assertTrue(dungeon.getEntitiesOnTile(0, 0).get(0) instanceof Boulder);
    }

    @Test
    public void boulderMovedOntoAnEnemyKillsIt() {
        DungeonMockController controller = setup4();

        Dungeon dungeon = controller.dungeon;

        assertEquals(1, dungeon.getEntitiesOnTile(0, 0).size());
        assertTrue(dungeon.getEntitiesOnTile(0, 0).get(0) instanceof Enemy);

        dungeon.getPlayer().move(Direction.LEFT);

        // check the enemy is no longer on that tile
        // and boulder is there instead
        assertEquals(1, dungeon.getEntitiesOnTile(0, 0).size());
        assertTrue(dungeon.getEntitiesOnTile(0, 0).get(0) instanceof Boulder);
        assertEquals(1, dungeon.getEntitiesOnTile(1, 0).size());
        assertTrue(dungeon.getEntitiesOnTile(1, 0).get(0) instanceof Player);
    }

}